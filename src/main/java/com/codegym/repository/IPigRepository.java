package com.codegym.repository;

import com.codegym.model.Cote;
import com.codegym.model.Pig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPigRepository extends JpaRepository<Pig, Integer> {
    Page<Pig> findPigsByStatus(Pageable pageable, String status);
//    Optional<List<Pig>> findPigsByStatusContaining(String status);
//    List<Pig> findPigsByWeight(double weight);
//    Optional<List<Pig>> findPigsByRoom(Cote room);
//    Optional<List<Pig>> findPigsByWeightBetween(double weightMin, double weightMax);
}
