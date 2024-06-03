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
    Page<Pig> findPigsByStatus(Pageable pageable, String status);
//    List<Pig> findPigsByWeight(double weight);
//    Optional<List<Pig>> findPigsByRoom(Cote room);
//    Optional<List<Pig>> findPigsByStatusContaining(String status);
//    Optional<List<Pig>> findPigsByWeightBetween(double weightMin, double weightMax);

}
