package com.codegym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Pig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateIn;
    private Date dateOut;
    private String status;
    private double weight;

    @ManyToOne
//    @JoinColumn(name = "cote_id", referencedColumnName = "id")
    private Cote room;
    public Pig() {
    }

}
