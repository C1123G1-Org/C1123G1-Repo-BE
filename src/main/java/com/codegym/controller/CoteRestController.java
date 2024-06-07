package com.codegym.controller;

import com.codegym.dto.ChangeCoteRequest;
import com.codegym.dto.CoteDto;
import com.codegym.model.Account;
import com.codegym.model.Cote;
import com.codegym.model.Pig;
import com.codegym.repository.IAccountRepository;
import com.codegym.service.IAccountService;
import com.codegym.service.ICoteService;
import com.codegym.service.IPigService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/cotes")
public class CoteRestController {
    @Autowired
    private ICoteService coteService;

    @Autowired
    private IPigService pigService;

    @Autowired
    private IAccountRepository accountRepository;

    @GetMapping("/getCodes")
    public ResponseEntity<List<Cote>> listCotes() {
        List<Cote> list = coteService.findCotesByDateCloseIsNull();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pigs")
    public ResponseEntity<List<Pig>> findAllPigByCote_Code(@RequestParam("id") int id) {
        Optional<List<Pig>> pigsOptional = pigService.findPigsByCote_IdAndDateOutIsNull(id);
        if (!pigsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pigsOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{pageSize}")
    public ResponseEntity<Page<Cote>> listCotesPage(@PathVariable Integer pageSize,
                                                    @RequestParam(value = "page") Integer page) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("dateOpen").descending());
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
        Optional<List<Cote>> listOptional = coteService.findByDateOpenBetween(startDate,endDate);
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
        Optional<List<Cote>> listOptional = coteService.findByDateOpenBetweenAndAccount_Code(startDate, endDate, code);
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
        Optional<List<Cote>> listOptional = coteService.findByDateCloseBetweenAndAccount_Code(startDate, endDate, code);
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
        BeanUtils.copyProperties(coteDto, cote);
        try {
            coteService.save(cote);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/changes")
    @Transactional
    public ResponseEntity<?> changesCote(@RequestBody ChangeCoteRequest object){
        try {
            Cote oldCote = object.getOldCote();
            oldCote.setQuantity(oldCote.getQuantity() - object.getPigs().size());
            if (oldCote.getQuantity() == 0) {
                oldCote.setDateClose(LocalDate.now());
            }
            coteService.update(oldCote);

            Cote newCote = object.getNewCote();
            newCote.setQuantity(newCote.getQuantity() + object.getPigs().size());
            coteService.update(newCote);

            List<Pig> pigList = new ArrayList<>();
            List<String> pigs = object.getPigs();
            for (String pig : pigs) {
                pigList.add(pigService.findPigsByCode(pig));
            }
            for (Pig pig : pigList) {
                pig.setCote(newCote);
                pigService.save(pig);
            }
        }catch (RuntimeException e) {
            throw new RuntimeException("Oops, something went wrong!");
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PutMapping("/updatePigsAfterExportCote")
    public ResponseEntity<Cote> updatePigs(@RequestParam("code") String code) {
        try {
        List<Pig> pigs = pigService.findPigsByCote_Code(code).get();
        for (int i = 0; i < pigs.size(); i++) {
            pigs.get(i).setDateOut(LocalDate.now());
            pigService.save(pigs.get(i));
        }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/findUser/{username}")
    public ResponseEntity<Account> findCote(@PathVariable String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}