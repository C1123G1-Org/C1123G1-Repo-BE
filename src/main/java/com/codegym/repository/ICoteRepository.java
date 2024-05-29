package com.codegym.repository;

import com.codegym.model.Cote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ICoteRepository extends JpaRepository<Cote,Integer> {
    Page<Cote> findByAccount_Code(Pageable pageable,String code);
    Page<Cote> findByDateOpenBetween (Pageable pageable, LocalDate startDate, LocalDate endDate);
    Page<Cote> findByDateCloseBetween (Pageable pageable, LocalDate startDate, LocalDate endDate);
}
