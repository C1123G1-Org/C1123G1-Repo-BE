package com.codegym.service;

import com.codegym.model.Cote;
import com.codegym.model.Pig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPigService {
    List<Pig> findAll();
    Page<Pig> findAll(Pageable pageable);
    void save(Pig pig);
    void update(int id,Pig pig);
    void remove(int id);
    Optional<Pig> findById(int id);
    Optional<List<Pig>> findPigsByStatus(String status);
    Optional<List<Pig>> findPigsByCote_Code(String code);
    Optional<List<Pig>> findByDateInBetween( LocalDate startDate, LocalDate endDate);
    Optional<List<Pig>> findByDateInBetweenAndCote_Code( LocalDate startDate, LocalDate endDate, String code);
    Optional<List<Pig>> findByDateOutBetween( LocalDate startDate, LocalDate endDate);
    Optional<List<Pig>> findByDateOutBetweenAndCote_Code( LocalDate startDate, LocalDate endDate, String code);
    Optional<List<Pig>> findPigsByWeightBetween(double weightMin, double weightMax);
    //
    Optional<List<Pig>> findPigsByCote_IdAndDateOutIsNull(int id);
    Pig findPigsByCode(String code);
}
