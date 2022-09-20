package com.example.blogapplication.service;

import com.example.blogapplication.model.dto.UsersDto;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public interface UsersService {
    UsersDto createUser(UsersDto usersDto);
    UsersDto updatedUser(UsersDto usersDto,Integer userId) throws UserNotFoundException;
    UsersDto updateUserByyId(Integer userId) throws UserNotFoundException;
    List<UsersDto> getAllUsers();
    void deleteUser(Integer userId) throws UserNotFoundException;
}
