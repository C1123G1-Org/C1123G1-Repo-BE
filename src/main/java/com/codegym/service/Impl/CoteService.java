package com.codegym.service.Impl;


import com.codegym.model.Cote;
import com.codegym.repository.ICoteRepository;
import com.codegym.service.ICoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CoteService implements ICoteService {
    @Autowired
    private ICoteRepository coteRepository;

    @Override
    public List<Cote> findAll() {
        return coteRepository.findAll();
    }

    @Override
    public Page<Cote> findAll(Pageable pageable) {
        return coteRepository.findAll(pageable);
    }

    @Override
    public void save(Cote cote){
        coteRepository.save(cote);
    }

    @Override
    public void update(Cote cote) {
        coteRepository.save(cote);
    }

    @Override
    public void remove(int id) {
        coteRepository.deleteById(id);
    }

    @Override
    public Optional<Cote> findById(int id) {
        return coteRepository.findById(id);
    }

    @Override
    public Optional<List<Cote>> findByAccount_Code(String code) {
        return coteRepository.findByAccount_Code(code);
    }

    @Override
    public Optional<List<Cote>> findByDateOpenBetween(LocalDate startDate, LocalDate endDate) {
        return coteRepository.findByDateOpenBetween(startDate,endDate);
    }

    @Override
    public Optional<List<Cote>> findByDateCloseBetween(LocalDate startDate, LocalDate endDate) {
        return coteRepository.findByDateCloseBetween(startDate,endDate);
    }
    @Override
    public Optional<List<Cote>> findByDateCloseBetweenAndAccount_Code(LocalDate startDate, LocalDate endDate, String code) {
        return coteRepository.findByDateCloseBetweenAndAccount_Code(startDate,endDate,code);
    }

    @Override
    public Optional<List<Cote>> findByDateOpenBetweenAndAccount_Code(LocalDate startDate, LocalDate endDate, String code) {
        return coteRepository.findByDateOpenBetweenAndAccount_Code(startDate,endDate,code);
    }

    @Override
    public List<Cote> findCotesByDateCloseIsNull() {
        return coteRepository.findCotesByDateCloseIsNull();
    }
}