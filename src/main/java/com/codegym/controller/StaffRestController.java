package com.codegym.controller;

import com.codegym.dto.AccountDto;
import com.codegym.dto.StaffDto;
import com.codegym.model.Account;
import com.codegym.model.Cote;
import com.codegym.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffRestController {

    @Autowired
    private IAccountService iAccountService;


    @GetMapping("")
    public ResponseEntity<?> getAllStaff(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "") String name) {
        System.out.println("a");
        Pageable pageable = PageRequest.of(page, 5);
        Page<Account> listStaff = iAccountService.findAllPage(pageable,name);
        return new ResponseEntity<>(listStaff, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStaff(@RequestBody Account account) {

        List<Account> accountList = iAccountService.findAll();

        String result = "";
        //1 tuong ung trung code, 2 trung username, 3 trung can cuoc
        for (int i = 0; i < accountList.size(); i++) {
            if (account.getCode().equals(accountList.get(i).getCode())){
                result += "1";
            }
            if (account.getUsername().equals(accountList.get(i).getUsername())){
                result += "2";
            }
            if (account.getIdentityCode().equals(accountList.get(i).getIdentityCode())){
                result+= "3";
            }
        }

        if (result == "") {
            iAccountService.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ByIdStaff(@PathVariable Integer id) {
        Account account = iAccountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable Integer id, @RequestBody Account account) {
        List<Account> accountList = iAccountService.findAll();

        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getId().equals(id)){
                accountList.remove(i);
                break;
            }
        }
        String result = "";
        //1 tuong ung trung code, 2 trung username, 3 trung can cuoc
        for (int i = 0; i < accountList.size(); i++) {
            if (account.getCode().equals(accountList.get(i).getCode())){
                result += "1";
            }
            if (account.getUsername().equals(accountList.get(i).getUsername())){
                result += "2";
            }
            if (account.getIdentityCode().equals(accountList.get(i).getIdentityCode())){
                result+= "3";
            }
        }

        if (result == "") {
            iAccountService.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeStaff(@PathVariable Integer id) {
        Account account = iAccountService.findById(id);
        iAccountService.delete(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("cote-a")
    public ResponseEntity<?> findCote(){
        List<Cote> coteList = iAccountService.findCoteManagement("trucvi");
        return new ResponseEntity<>(coteList,HttpStatus.OK);
    }




}