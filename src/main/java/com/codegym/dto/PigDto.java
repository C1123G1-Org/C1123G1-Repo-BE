package com.codegym.dto;

import com.codegym.model.Cote;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PigDto {
    private int id;
    @NotBlank
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    private LocalDate dateIn;

    private LocalDate dateOut;

//    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{error.name.regex}")
    private String status;

//    @Pattern(regexp = "^[0-9\\s]+$", message = "{error.name.regex}")
    @Min(1)
    private double weight;
    @NotNull
    private Cote cote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PigDto that = (PigDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
