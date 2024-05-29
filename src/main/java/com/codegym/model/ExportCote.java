package com.codegym.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportCote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String partner;
    private double weight;
    private int amount;
    @CreationTimestamp
    private LocalDateTime dateExport;
    private double price;

    @ManyToOne
    @JoinColumn(name = "cote_id", referencedColumnName = "id")
    private Cote cote;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
