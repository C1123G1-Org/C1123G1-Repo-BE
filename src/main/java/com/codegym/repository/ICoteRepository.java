package com.codegym.repository;

import com.codegym.model.Cote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICoteRepository extends JpaRepository<Cote,Integer> {
    Optional<List<Cote>> findByAccount_Code(String code);
    Optional<List<Cote>> findByDateOpenBetween ( LocalDate startDate, LocalDate endDate);
    Optional<List<Cote>> findByDateCloseBetween ( LocalDate startDate, LocalDate endDate);
    Optional<List<Cote>> findByDateCloseBetweenAndAccount_Code ( LocalDate startDate, LocalDate endDate, String code);
    Optional<List<Cote>> findByDateOpenBetweenAndAccount_Code ( LocalDate startDate, LocalDate endDate, String code);
    List<Cote> findCotesByDateCloseIsNull();



}