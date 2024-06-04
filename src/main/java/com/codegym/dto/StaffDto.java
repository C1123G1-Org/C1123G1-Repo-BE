package com.codegym.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;


public interface StaffDto {
    int getId();

    String getCode();

    String getUsername();

    String getPassword();

    String getFullName();

    String getEmail();

    Boolean getGender();

    String getIdentityCode();

    LocalDate getDate();
}
