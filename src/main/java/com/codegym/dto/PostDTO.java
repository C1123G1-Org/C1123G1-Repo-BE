package com.codegym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private String image;
    private String status = "POSTED";
    private LocalDateTime postDate;
    private int accountId;
}
