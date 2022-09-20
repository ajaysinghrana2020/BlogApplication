package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post,Integer> {
}
