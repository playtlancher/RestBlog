package org.blog.blog.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {
    private String text;
    private LocalDate date = LocalDate.now();
    private int userId;
    private int postId;
}
