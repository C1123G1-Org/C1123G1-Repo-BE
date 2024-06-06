package com.codegym.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDto {
    private Integer page;
    private Integer Size;
    private String SortDirection;
    private Integer SortBy;



}
