package com.example.blogapplication.service.impel;

import com.example.blogapplication.model.dto.UsersDto;
import com.example.blogapplication.model.entities.Users;
import com.example.blogapplication.repository.UsersRepository;
import com.example.blogapplication.service.UserNotFoundException;
import com.example.blogapplication.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersDto createUser(UsersDto usersDto) {
        Users users= this.dtoToUser(usersDto);
        Users savedUser = this.dtoToUser(usersDto);
        return null;
    }

    @Override
    public UsersDto updatedUser(UsersDto usersDto, Integer userId) throws UserNotFoundException {
        Users users = this.usersRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("Users","id",userId));
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(users.getPassword());
        Users updatedUser =this.usersRepository.save(users);
        UsersDto usersDtos = this.userToDto(updatedUser);

        return usersDtos;
    }

    @Override
    public UsersDto updateUserByyId(Integer userId) throws UserNotFoundException {
        Users users = this.usersRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("Users","id",userId));

        return this.userToDto(users);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users =this.usersRepository.findAll();
        List<UsersDto> usersDtos =users.stream().map(users1 ->this.userToDto(users1)).collect(Collectors.toList());

        return usersDtos;
    }

    @Override
    public void deleteUser(Integer userId) throws UserNotFoundException {
        Users users = this.usersRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("Users","id",userId));
        this.usersRepository.delete(users);

    }
    public Users dtoToUser(UsersDto usersDto){
        Users users =new Users();
        users.setId(usersDto.getId());
        users.setName(usersDto.getName());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        return users;
    }
    public UsersDto userToDto(Users users){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setName(users.getName());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        return usersDto;
    }

}
