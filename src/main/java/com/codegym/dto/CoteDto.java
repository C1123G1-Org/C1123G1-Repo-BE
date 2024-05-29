package com.codegym.dto;

import com.codegym.model.Account;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoteDto {

    private int id;
    @NotBlank
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    private LocalDate dateOpen;
    private LocalDate dateClose;
    @Min(0)
    @Max(15)
    private int quantity;
    @NotNull
    private Account account;
}
