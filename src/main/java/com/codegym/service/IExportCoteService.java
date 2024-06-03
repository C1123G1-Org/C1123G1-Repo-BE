package com.codegym.service;

import com.codegym.dto.ExportCoteRequest;
import com.codegym.model.ExportCote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IExportCoteService {
    Page<ExportCote> findAll(Pageable pageable);

    ExportCote addExportCote(ExportCoteRequest exportCoteRequest);

    ExportCote updateExportCote(ExportCoteRequest exportCoteRequest);

    void deleteExportCote(int id);
}
