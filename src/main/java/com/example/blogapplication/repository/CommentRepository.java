package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

//    @Query(value = "select u from User u where u.comment = :#{#custo.firstname}",nativeQuery = true)
//    Optional<String> geSpecificComment(@Param("name")String name);

}
