package com.codegym.controller;


import com.codegym.dto.PigDto;
import com.codegym.model.Cote;
import com.codegym.model.Pig;
import com.codegym.service.ICoteService;
import com.codegym.service.IPigService;
import com.codegym.service.Impl.PigService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/pigs")
public class PigRestController {
    @Autowired
    private IPigService pigService;
    @Autowired
    private ICoteService coteService;
    private static void addElement(Map<LocalDate, Integer> elementCount, LocalDate element) {
        if (elementCount.containsKey(element)) {
            elementCount.put(element, elementCount.get(element) + 1);
        } else {
            elementCount.put(element, 1);
        }
    }

    @GetMapping("/coteList")
    public ResponseEntity<List<Cote>> listCote(){
        List<Cote> coteList = coteService.findAll();
        List<Cote> coteListAvaiable = new ArrayList<>();
        for (Cote c: coteList) {
            if (c.getDateClose() == null ) {
                coteListAvaiable.add(c);
            }
        }
        if (coteListAvaiable.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coteListAvaiable,HttpStatus.OK);
    }
    @GetMapping("/dateInList")
    public ResponseEntity<Map<LocalDate, Integer>> listDateIn(){
        List<Pig> list = pigService.findAll();
        Map<LocalDate, Integer> listMap = new TreeMap<>();
        for (Pig p: list) {
            addElement(listMap, p.getDateIn());
        }
        return new ResponseEntity<>(listMap,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Pig>> listPig(){
        List<Pig> list = pigService.findAll();
        Comparator<Pig> byDate = Comparator.comparing(Pig::getDateIn);
        Collections.sort(list, byDate);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/{pageSize}")
    public ResponseEntity<Page<Pig>> listPigPage(@PathVariable Integer pageSize,
                                                 @RequestParam(value = "page") Integer page){
        Pageable pageable = PageRequest.of(page,pageSize, Sort.by("id").descending());
        Page<Pig> list = pigService.findAll(pageable);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PigDto> createPig(@Valid @RequestBody PigDto pigDto){
        Pig addPig = new Pig();
        BeanUtils.copyProperties(pigDto, addPig);
        Cote cote = pigDto.getCote();
        cote.setQuantity(cote.getQuantity()+1);
        coteService.save(cote);
        pigService.save(addPig);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Pig> findPig(@PathVariable int id){
        Optional<Pig> PigOptional = pigService.findById(id);
        if (!PigOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(PigOptional.get(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pig> deletePig(@PathVariable Integer id){
        Optional<Pig> PigOptional = pigService.findById(id);
        if (!PigOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cote coteOld = PigOptional.get().getCote();
        coteOld.setQuantity(coteOld.getQuantity()-1);
        if (coteOld.getQuantity() == 0) {
            coteOld.setDateClose(LocalDate.now());
        }
        coteService.save(coteOld);
        pigService.remove(id);
        return new ResponseEntity<>(PigOptional.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pig> updatePig(@Valid @PathVariable Integer id, @RequestBody PigDto pigDto){
        Optional<Pig> PigOptional = pigService.findById(id);
        if (!PigOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cote coteOld = PigOptional.get().getCote();
        coteOld.setQuantity(coteOld.getQuantity()-1);
        if (coteOld.getQuantity() == 0) {
            coteOld.setDateClose(LocalDate.now());
        }
        Cote coteNew = pigDto.getCote();
        coteNew.setQuantity(coteNew.getQuantity()+1);
        coteService.save(coteOld);

        coteService.save(coteNew);
        Pig updatePig = new Pig();
        BeanUtils.copyProperties(pigDto, updatePig);
        pigService.update(id, updatePig);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /////////////////////////////
    @GetMapping("/search")
    public ResponseEntity<List<Pig>> searchCodeCote(@RequestParam("code") String code) {
        Optional<List<Pig>> pigOptional = pigService.findPigsByCote_Code(code);
        if (!pigOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pigOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/search/in")
    public ResponseEntity<List<Pig>> searchInTime(@RequestParam("startDate") LocalDate startDate,
                                                     @RequestParam("endDate") LocalDate endDate) {
        Optional<List<Pig>> listOptional = pigService.findByDateInBetween(startDate, endDate);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/search/in/cote")
    public ResponseEntity<List<Pig>> searchInTimeAndCote(@RequestParam("startDate") LocalDate startDate,
                                                               @RequestParam("endDate") LocalDate endDate,
                                                               @RequestParam("code") String code) {
        Optional<List<Pig>> listOptional = pigService.findByDateInBetweenAndCote_Code(startDate, endDate, code);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/search/out")
    public ResponseEntity<List<Pig>> searchOutTime(@RequestParam("startDate") LocalDate startDate,
                                                      @RequestParam("endDate") LocalDate endDate) {
        Optional<List<Pig>> listOptional = pigService.findByDateOutBetween(startDate, endDate);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/search/out/cote")
    public ResponseEntity<List<Pig>> searchOutTimeAndCote(@RequestParam("startDate") LocalDate startDate,
                                                                @RequestParam("endDate") LocalDate endDate,
                                                                @RequestParam("code") String code) {
        Optional<List<Pig>> listOptional = pigService.findByDateOutBetweenAndCote_Code(startDate, endDate, code);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(), HttpStatus.OK);
    }

}
