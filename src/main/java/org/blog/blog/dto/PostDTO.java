package org.blog.blog.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDTO {
    private String title;
    private String text;
    private String smallDescription;
    private LocalDate date = LocalDate.now();
    private int userId;
}
