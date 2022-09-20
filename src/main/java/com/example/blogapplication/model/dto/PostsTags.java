package com.example.blogapplication.model.dto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostsTags {
    private String title;
    private String content;
    private String author;
    private String tags;
}
