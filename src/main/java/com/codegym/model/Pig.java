package com.codegym.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private String status;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "cote_id", referencedColumnName = "id")
    private Cote cote;

}
