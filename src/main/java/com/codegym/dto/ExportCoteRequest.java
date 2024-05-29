package com.codegym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportCoteRequest {
    private int id;
    private String partner;
    private double weight;
    private int amount;
    private double price;
    private int cote_id;
    private int account_id;
    private String dateExport;
}
