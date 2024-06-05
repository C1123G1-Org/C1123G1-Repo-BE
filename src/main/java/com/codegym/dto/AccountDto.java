package com.codegym.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private int id;

    private String code;

    private String username;
    private String password;
    private String fullName;
    private String email;
    private Boolean gender;

    private String identityCode;
    private LocalDate date;
}
