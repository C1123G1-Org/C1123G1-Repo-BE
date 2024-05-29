package com.codegym.service.Impl;

import com.codegym.dto.ExportCoteRequest;
import com.codegym.model.ExportCote;
import com.codegym.repository.IAccountRepository;
import com.codegym.repository.ICoteRepository;
import com.codegym.repository.IExportCoteRepository;
import com.codegym.service.IExportCoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExportCoteService implements IExportCoteService {
    @Autowired
    private IExportCoteRepository iExportCoteRepository;
    @Autowired
    private ICoteRepository iCoteRepository;
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public Page<ExportCote> findAll(Pageable pageable) {
        return iExportCoteRepository.findAll(pageable);
    }

    @Override
    public ExportCote addExportCote(ExportCoteRequest exportCoteRequest) {
        ExportCote exportCote = new ExportCote();
        exportCote.setWeight(exportCoteRequest.getWeight());
        exportCote.setAmount(exportCoteRequest.getAmount());
        exportCote.setPartner(exportCoteRequest.getPartner());
        exportCote.setPrice(exportCoteRequest.getPrice());
        exportCote.setCote(iCoteRepository.findById(exportCoteRequest.getCote_id()).get());
        exportCote.setAccount(iAccountRepository.findById(exportCoteRequest.getAccount_id()).get());
        return iExportCoteRepository.save(exportCote);
    }

    @Override
    public ExportCote updateExportCote(ExportCoteRequest exportCoteRequest) {
        ExportCote exportCote = new ExportCote();
        exportCote.setId(exportCoteRequest.getId());
        exportCote.setWeight(exportCoteRequest.getWeight());
        exportCote.setAmount(exportCoteRequest.getAmount());
        exportCote.setPartner(exportCoteRequest.getPartner());
        exportCote.setPrice(exportCoteRequest.getPrice());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateExport = LocalDateTime.parse(exportCoteRequest.getDateExport(), formatter);
        exportCote.setDateExport(dateExport);
        exportCote.setCote(iCoteRepository.findById(exportCoteRequest.getCote_id()).get());
        exportCote.setAccount(iAccountRepository.findById(exportCoteRequest.getAccount_id()).get());
        return iExportCoteRepository.save(exportCote);
    }

    @Override
    public void deleteExportCote(int id) {
        iExportCoteRepository.deleteById(id);
    }


}
