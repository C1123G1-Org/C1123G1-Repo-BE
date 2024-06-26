package com.codegym.service.Impl;

import com.codegym.model.Cote;
import com.codegym.model.Pig;
import com.codegym.repository.IPigRepository;
import com.codegym.service.IPigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class PigService implements IPigService {
    @Autowired
    private IPigRepository pigRepository;
    @Override
    public List<Pig> findAll() {
        return pigRepository.findAll();
    }

    @Override
    public Page<Pig> findAll(Pageable pageable) {
        return pigRepository.findAll(pageable);
    }

    @Override
    public void save(Pig pig) {
        pigRepository.save(pig);
    }

    @Override
    public void update(int id, Pig pig) {
        pigRepository.save(pig);
    }

    @Override
    public void remove(int id) {
        pigRepository.deleteById(id);
    }
    @Override
    public Optional<Pig> findById(int id) {
        return pigRepository.findById(id);
    }

    @Override
    public Optional<List<Pig>> findPigsByStatus(String status) {
        return pigRepository.findPigsByStatus(status);
    }

    @Override
    public Optional<List<Pig>> findPigsByCote_Code(String code) {
        return pigRepository.findPigsByCote_Code(code);
    }

    @Override
    public Optional<List<Pig>> findByDateInBetween(LocalDate startDate, LocalDate endDate) {
        return pigRepository.findByDateInBetween(startDate,endDate);
    }

    @Override
    public Optional<List<Pig>> findByDateInBetweenAndCote_Code(LocalDate startDate, LocalDate endDate, String code) {
        return pigRepository.findByDateInBetweenAndCote_Code(startDate, endDate, code);
    }

    @Override
    public Optional<List<Pig>> findByDateOutBetween(LocalDate startDate, LocalDate endDate) {
        return pigRepository.findByDateOutBetween(startDate, endDate);
    }

    @Override
    public Optional<List<Pig>> findByDateOutBetweenAndCote_Code(LocalDate startDate, LocalDate endDate, String code) {
        return pigRepository.findByDateOutBetweenAndCote_Code(startDate, endDate, code);
    }

    @Override
    public Optional<List<Pig>> findPigsByWeightBetween(double weightMin, double weightMax) {
        return pigRepository.findPigsByWeightBetween(weightMin, weightMax);
    }

    @Override
    public Optional<List<Pig>> findPigsByCote_IdAndDateOutIsNull(int id) {
        return pigRepository.findPigsByCote_IdAndDateOutIsNull(id);
    }
    @Override
    public Pig findPigsByCode(String code) {
        return pigRepository.findPigsByCode(code);
    }
}
