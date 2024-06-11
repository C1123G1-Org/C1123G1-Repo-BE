package com.codegym.dto.account_information_DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {
    private String code;
    private String username;
    private String fullName;
    private String email;
    private Boolean gender;
    private String identityCode;
    private LocalDate date;
}
