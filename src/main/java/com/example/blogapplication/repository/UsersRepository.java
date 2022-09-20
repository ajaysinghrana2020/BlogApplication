package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    @Query(value = "select * from comments where user_id = :value",nativeQuery = true)
    List<Users> getUserDetails(int value);
}
