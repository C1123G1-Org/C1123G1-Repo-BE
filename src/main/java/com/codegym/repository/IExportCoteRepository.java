package com.codegym.repository;

import com.codegym.model.ExportCote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExportCoteRepository extends JpaRepository<ExportCote, Integer> {

}
