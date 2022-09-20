package com.example.blogapplication.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends Throwable {
    String resourceName;
    String fieldName;
    long fieldValue;
    public UserNotFoundException(String message) {
        super(message);
    }
}
