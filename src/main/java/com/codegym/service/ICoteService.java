package com.codegym.service;

import com.codegym.model.Cote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICoteService {
    List<Cote> findAll();
    Page<Cote> findAll(Pageable pageable);
    void save(Cote cote);
    void update(Cote cote);
    void remove(int id);
    Optional<Cote> findById(int id);
    Page<Cote> findByAccount_Code(Pageable pageable,String code);
    Page<Cote> findByDateOpenBetween (Pageable pageable, LocalDate startDate, LocalDate endDate);
    Page<Cote> findByDateCloseBetween (Pageable pageable, LocalDate startDate, LocalDate endDate);
}
