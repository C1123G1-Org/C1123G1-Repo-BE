package com.codegym.controller;

import com.codegym.dto.DeleteContactInfoRequest;
import com.codegym.model.ContactInfo;
import com.codegym.service.IContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/contact-info")
public class ContactInfoController {
    @Autowired
    private IContactInfoService iContactInfoService;

    @GetMapping
    public ResponseEntity<?> getAllContactInfo(@RequestParam(defaultValue = "0") int page){
        Page<ContactInfo> contactInfos = iContactInfoService.findAll(PageRequest.of(page, 5));
        return new ResponseEntity<>(contactInfos , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addContactInfo(@RequestBody ContactInfo contactInfo){
        try {
            ContactInfo contactInfo1 = iContactInfoService.addContactInfo(contactInfo);
            return new ResponseEntity<>(contactInfo1 ,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteContactInfos(@RequestBody DeleteContactInfoRequest deleteContactInfoRequest){
        try{
            iContactInfoService.deleteContactInfo(deleteContactInfoRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
