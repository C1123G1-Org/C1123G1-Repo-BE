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
    public Page<Pig> findPigsByStatus(Pageable pageable, String status) {
        return pigRepository.findPigsByStatus(pageable, status);
    }

    @Override
    public Optional<List<Pig>> findPigsByCote_CodeAndDateOutIsNull(String code) {
        return pigRepository.findPigsByCote_CodeAndDateOutIsNull(code);
    }

    //    @Override
//    public List<Pig> findPigsByWeight(double weight) {
//        return pigRepository.findPigsByWeight(weight);
//    }
//
//    @Override
//    public Optional<List<Pig>> findPigsByRoom(Cote room) {
//        return pigRepository.findPigsByRoom(room);
//    }
//
//    @Override
//    public Optional<List<Pig>> findPigsByStatusContaining(String status) {
//        return pigRepository.findPigsByStatusContaining(status);
//    }
//
//    @Override
//    public Optional<List<Pig>> findPigsByWeightBetween(double weightMin, double weightMax) {
//        return pigRepository.findPigsByWeightBetween(weightMin, weightMax);
//    }
}
