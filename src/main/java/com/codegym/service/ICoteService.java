package com.codegym.service;

import com.codegym.model.Cote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICoteService {
    List<Cote> findAll();
    Page<Cote> findAll(Pageable pageable);
    void save(Cote cote);
    void update(Cote cote);
    void remove(int id);
    Optional<Cote> findById(int id);
//    List<Cote> findCotesByNameCote(String name);
//    Optional<List<Cote>> findCotesByNameCoteContaining(String keyword);
//    Optional<Cote> findCoteByNameCoteContaining(String keyword);
//    List<Cote> findCotesByTypeCoteContaining(String type);
//    List<Cote> findCotesByProducer(String producer);
}
