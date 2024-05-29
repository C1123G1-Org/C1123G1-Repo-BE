package com.codegym.repository;

import com.codegym.model.Cote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoteRepository extends JpaRepository<Cote,Integer> {
}
