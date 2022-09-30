package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users,Integer> {
}
