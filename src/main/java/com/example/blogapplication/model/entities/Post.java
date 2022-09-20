package com.example.blogapplication.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String excerpt;
    private String content;
    private String author;
    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    @Column(name = "is_published")
    private Boolean isPublished;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
