package com.codegym.controller;


import com.codegym.dto.PigDto;
import com.codegym.model.Cote;
import com.codegym.model.Pig;
import com.codegym.service.ICoteService;
import com.codegym.service.IPigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/pigs")
public class PigRestController {
    @Autowired
    IPigService pigService;
    @Autowired
    ICoteService coteService;

    @GetMapping
    public ResponseEntity<List<Pig>> listPigs(){
        List<Pig> list = pigService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Pig pig: list) {
            System.out.println(pig.toString());
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/statusSearch/{status}")
    public ResponseEntity<List<Pig>> listPigsSearchByStatus(@PathVariable String status){
        Optional<List<Pig>> list = pigService.findPigsByStatus(status);
        if (list.isEmpty()){
            System.out.println("Bad");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Pig pig: list.get()) {
            System.out.println(pig.toString());
        }
        return new ResponseEntity<>(list.get(),HttpStatus.OK);
    }
    @GetMapping("/search/weight")
    public ResponseEntity<List<Pig>> searchOpenTime(
                                                     @RequestParam("weightMin")double weightMin,
                                                     @RequestParam("weightMax")double weightMax){
        Optional<List<Pig>> list = pigService.findPigsByWeightBetween(weightMin,weightMax);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PigDto> createPig(@Validated @RequestBody PigDto pigDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Pig addPig = new Pig();
        BeanUtils.copyProperties(pigDto, addPig);
        Cote cote = pigDto.getRoom();
        cote.setQuantity(cote.getQuantity()+1);
        coteService.save(cote);
        pigService.save(addPig);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Pig> findPig(@PathVariable int id){
//        Optional<Pig> PigOptional = pigService.findById(id);
//        if (!PigOptional.isPresent()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(PigOptional.get(),HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pig> deletePig(@PathVariable int id){
        Optional<Pig> PigOptional = pigService.findById(id);
        if (!PigOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cote coteOld = PigOptional.get().getRoom();
        coteOld.setQuantity(coteOld.getQuantity()-1);
        if (coteOld.getQuantity() == 0) {
            coteOld.setDateClose(LocalDate.now());
        }
        coteService.save(coteOld);
        pigService.remove(id);
        return new ResponseEntity<>(PigOptional.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pig> updatePig(@PathVariable int id, @RequestBody PigDto pigDto){
        Optional<Pig> PigOptional = pigService.findById(id);
        if (!PigOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cote coteOld = PigOptional.get().getRoom();
        coteOld.setQuantity(coteOld.getQuantity()-1);
        if (coteOld.getQuantity() == 0) {
            coteOld.setDateClose(LocalDate.now());
        }
        Cote coteNew = pigDto.getRoom();
        coteNew.setQuantity(coteNew.getQuantity()+1);
        coteService.save(coteOld);

        coteService.save(coteNew);
        Pig updatePig = new Pig();
        BeanUtils.copyProperties(pigDto, updatePig);
        pigService.update(id, updatePig);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
