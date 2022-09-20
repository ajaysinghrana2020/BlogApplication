package com.example.blogapplication.controller;

import com.example.blogapplication.model.dto.UsersDto;
import com.example.blogapplication.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    UsersService usersService;
//
//    @PostMapping ("/")
//    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto){
//        UsersDto createUserDto =this.usersService.createUser(usersDto);
//        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
//    }
//}
