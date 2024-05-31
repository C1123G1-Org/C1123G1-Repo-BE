package com.codegym.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class StaffDto {
    private  Integer page;

    private Integer size;

    private String sortBy;

    private Sort.Direction sortDirection;
    private String name;
}
