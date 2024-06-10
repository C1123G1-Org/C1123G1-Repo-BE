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
    @NotNull(message = "không được để trống")
    private LocalDate dateOpen;
    private LocalDate dateClose;
    @Min(value = 0,message = "Thấp nhất là 0")
    @Max(30)
    private int quantity;
    @NotNull
    private Integer account_id;
}