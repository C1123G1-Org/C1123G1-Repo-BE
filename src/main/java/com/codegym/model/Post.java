package com.codegym.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(columnDefinition = "LONGBLOB")
    private String content;
    private String image;
    private String status;
    private Date postDate;

    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Post() {
    }

}
