package com.codegym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Getter
@Setter
public class ExportCote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String partner;
    private double weight;
    private int amount;
    private Date dateExport;
    private double price;

    @ManyToOne
//    @JoinColumn(name = "cote_id", referencedColumnName = "id")
    private Cote cote;

    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public ExportCote() {
    }

}
