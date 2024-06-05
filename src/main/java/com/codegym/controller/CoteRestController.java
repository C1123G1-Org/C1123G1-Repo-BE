package com.codegym.controller;

import com.codegym.dto.CoteDto;
import com.codegym.model.Cote;
import com.codegym.service.ICoteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cotes")
public class CoteRestController {
    @Autowired
    private ICoteService coteService;

    @GetMapping
    public ResponseEntity<List<Cote>> listCotes() {
        List<Cote> list = coteService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,
                HttpStatus.OK);
    }

    @GetMapping("/{pageSize}")
    public ResponseEntity<Page<Cote>> listCotesPage(@PathVariable Integer pageSize,
                                                    @RequestParam(value = "page") Integer page) {
        Pageable pageable = PageRequest.of(page,
                pageSize,
                Sort
                        .by("dateOpen")
                        .descending());
        Page<Cote> list = coteService.findAll(pageable);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,
                HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Cote>> searchCodeAccount(@RequestParam("code") String code) {
        Optional<List<Cote>> coteOptional = coteService.findByAccount_Code(code);
        if (!coteOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(coteOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping("/search/open")
    public ResponseEntity<List<Cote>> searchOpenTime(@RequestParam("startDate") LocalDate startDate,
                                                     @RequestParam("endDate") LocalDate endDate) {
        Optional<List<Cote>> listOptional = coteService.findByDateOpenBetween(startDate,
                endDate);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping("/search/open/account")
    public ResponseEntity<List<Cote>> searchOpenTimeAndAccount(@RequestParam("startDate") LocalDate startDate,
                                                               @RequestParam("endDate") LocalDate endDate,
                                                               @RequestParam("code") String code) {
        Optional<List<Cote>> listOptional = coteService.findByDateOpenBetweenAndAccount_Code(startDate,
                endDate,
                code);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping("/search/close")
    public ResponseEntity<List<Cote>> searchCloseTime(@RequestParam("startDate") LocalDate startDate,
                                                      @RequestParam("endDate") LocalDate endDate) {
        Optional<List<Cote>> listOptional = coteService.findByDateCloseBetween(startDate,
                endDate);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping("/search/close/account")
    public ResponseEntity<List<Cote>> searchCloseTimeAndAccount(@RequestParam("startDate") LocalDate startDate,
                                                                @RequestParam("endDate") LocalDate endDate,
                                                                @RequestParam("code") String code) {
        Optional<List<Cote>> listOptional = coteService.findByDateCloseBetweenAndAccount_Code(startDate,
                endDate,
                code);
        if (!listOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listOptional.get(),
                HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Cote> findCote(@PathVariable Integer id) {
        Optional<Cote> coteOptional = coteService.findById(id);
        if (!coteOptional.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(coteOptional.get(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cote> createCote(@Valid @RequestBody CoteDto coteDto) {
        Cote cote = new Cote();
        BeanUtils.copyProperties(coteDto,
                cote);
        try {
            coteService.save(cote);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cote> updateCote(@Valid @RequestBody CoteDto coteDto,
                                           @PathVariable Integer id) {
        Cote cote = new Cote();
        BeanUtils.copyProperties(coteDto,
                cote);
        try {
            coteService.update(cote);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cote> deleteCote(@PathVariable Integer id) {
        Optional<Cote> coteOptional = coteService.findById(id);
        if (coteOptional.isPresent()) {
            coteService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}