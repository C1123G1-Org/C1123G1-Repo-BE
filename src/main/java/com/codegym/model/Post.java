package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String image;
    private String status;
    private LocalDateTime postDate;
    private boolean isFocalPoint;

    @ManyToOne
    @JoinColumn(name = "account_id",
            referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "code", "email", "gender", "identityCode", "status"})
    private Account account;


}
