package com.codegym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    @NotBlank
    private double weight;
    @NotNull
    @NotEmpty
    @NotBlank
    private int amount;
    @NotNull
    @NotEmpty
    @NotBlank
    private double price;
    @NotNull
    @NotEmpty
    @NotBlank
    private int cote_id;
    private int account_id;
    private String dateExport;
}
