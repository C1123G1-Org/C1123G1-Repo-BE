package com.codegym.controller;

import com.codegym.model.Cote;
import com.codegym.service.ICoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cotes")
public class CoteRestController {
    @Autowired
    ICoteService coteService;

    @GetMapping
    public ResponseEntity<List<Cote>> listCotes(){
        List<Cote> list = coteService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Cote> createCote(@RequestBody Cote cote){
        coteService.save(cote);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
