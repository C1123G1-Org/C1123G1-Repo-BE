package com.codegym.controller;

import com.codegym.dto.ExportCoteRequest;
import com.codegym.model.ExportCote;
import com.codegym.service.IExportCoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("api/exportcotes")
public class ExportCoteRestController {
    @Autowired
    private IExportCoteService iExportCoteService;

    @GetMapping
    public ResponseEntity<?> getAllExportCotes(@RequestParam(defaultValue = "0") int page){
        Page<ExportCote> exportCotes = iExportCoteService.findAll(PageRequest.of(page, 5));
        return new ResponseEntity<>(exportCotes ,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addExportCotes(@RequestBody ExportCoteRequest exportCoteRequest){
        try {
            ExportCote exportCote = iExportCoteService.addExportCote(exportCoteRequest);
            return new ResponseEntity<>(exportCote ,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateExportCote(@RequestBody ExportCoteRequest exportCoteRequest){
        try {
            ExportCote exportCote = iExportCoteService.updateExportCote(exportCoteRequest);
            return new ResponseEntity<>(exportCote ,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExportCote(@PathVariable int id){
        try {
            iExportCoteService.deleteExportCote(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
