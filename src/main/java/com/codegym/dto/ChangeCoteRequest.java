package com.codegym.dto;

import com.codegym.model.Cote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeCoteRequest {
    private Cote oldCote;
    private Cote newCote;
    private List<String> pigs;
}
