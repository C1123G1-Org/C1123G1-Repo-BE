package com.codegym.dto;

import com.codegym.model.Account;
import com.codegym.model.Cote;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportCoteResponse {
    private int id;
    private String partner;
    private double weight;
    private int amount;
    private String dateExport;
    private double price;
    private Cote cote;
    private Account account;
}
