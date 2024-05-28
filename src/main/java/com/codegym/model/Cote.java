package com.codegym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Cote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String code;
    private Date dateOpen;
    private Date dateClose;
    private int quantity;

    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Cote() {
    }

}
