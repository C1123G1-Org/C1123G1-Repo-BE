package com.codegym.controller;

import com.codegym.dto.CoteDto;
import com.codegym.model.Cote;
import com.codegym.service.ICoteService;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/cotes")
public class CoteRestController {
    @Autowired
    private ICoteService coteService;

//    @GetMapping
//    public ResponseEntity<List<Cote>> listCotes(){
//        List<Cote> list = coteService.findAll();
//        if (list.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(list,HttpStatus.OK);
//    }

    @GetMapping("/{pageSize}")
    public ResponseEntity<Page<Cote>> listCotesPage(@PathVariable Integer pageSize,@RequestParam(value = "page") Integer page){
        Pageable pageable = PageRequest.of(page,pageSize, Sort.by("dateOpen").descending());
        Page<Cote> list = coteService.findAll(pageable);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Cote>> searchCodeAccount(@PageableDefault(value = 3) Pageable pageable,
                                                        @RequestParam("code") String code){
        Page<Cote> list = coteService.findByAccount_Code(pageable,code);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/search/open")
    public ResponseEntity<Page<Cote>> searchOpenTime(@PageableDefault(value = 3) Pageable pageable,
                                                     @RequestParam("startDate")LocalDate startDate,
                                                     @RequestParam("endDate")LocalDate endDate){
        Page<Cote> list = coteService.findByDateOpenBetween(pageable,startDate,endDate);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/search/close")
    public ResponseEntity<Page<Cote>> searchCloseTime(@PageableDefault(value = 3) Pageable pageable,
                                                      @RequestParam("startDate")LocalDate startDate,
                                                      @RequestParam("endDate")LocalDate endDate){
        Page<Cote> list = coteService.findByDateCloseBetween(pageable,startDate,endDate);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Cote> findCote(@PathVariable Integer id){
        Optional<Cote> coteOptional = coteService.findById(id);
        if (!coteOptional.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(coteOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cote> createCote(@Valid @RequestBody CoteDto coteDto){
        Cote cote  = new Cote();
        BeanUtils.copyProperties(coteDto,cote);
        try {
            coteService.save(cote);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cote> updateCote(@Valid @RequestBody CoteDto coteDto,
                                           @PathVariable Integer id){
        Cote cote = new Cote();
        BeanUtils.copyProperties(coteDto,cote);
        try {
            coteService.update(cote);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cote> deleteCote(@PathVariable Integer id){
        Optional<Cote> coteOptional = coteService.findById(id);
        if (coteOptional.isPresent()){
            coteService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}