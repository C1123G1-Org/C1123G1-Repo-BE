package com.codegym.controller;

import com.codegym.model.Account;
import com.codegym.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("staff")
public class StaffRestController {
    @Autowired
    private IAccountService iAccountService;


    @GetMapping
    public ResponseEntity<?> getAllStaff() {
        List<Account> listStaff = iAccountService.findAll();
        return new ResponseEntity<>(listStaff, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStaff(@RequestBody Account account) {
        iAccountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable Integer id,@RequestBody Account account) {
        Account ok = iAccountService.findById(id);
        iAccountService.updateS(account);
        return new ResponseEntity<>(ok,HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeStaff( @PathVariable Integer id) {
        Account account = iAccountService.findById(id);
        iAccountService.delete(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
