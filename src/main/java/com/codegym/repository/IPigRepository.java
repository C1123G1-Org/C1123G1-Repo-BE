package com.codegym.repository;

import com.codegym.model.Cote;
import com.codegym.model.Pig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPigRepository extends JpaRepository<Pig, Integer> {
    Optional<List<Pig>> findPigsByStatus(String status);
    Optional<List<Pig>> findPigsByStatusContaining(String status);
    List<Pig> findPigsByWeight(double weight);
    Optional<List<Pig>> findPigsByWeightIsAfterAndWeightIsBefore (float weightMin, float weightMax);
    Optional<List<Pig>> findPigsByWeightIsAfter(float weightMin);
    Optional<List<Pig>> findPigsByWeightIsBefore (float weightMax);
    Optional<List<Pig>> findPigsByRoom(Cote room);
    Optional<List<Pig>> findPigsByWeightBetween(double weightMin, double weightMax);
}
