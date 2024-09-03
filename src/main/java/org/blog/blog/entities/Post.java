package org.blog.blog.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String text;
    private String smallDescription;
    private LocalDate date = LocalDate.now();
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Post(String title, String smallDescription, String text , User user) {
        this.title = title;
        this.text = text;
        this.smallDescription = smallDescription;
        this.user = user;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", smallDescription='" + smallDescription + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
