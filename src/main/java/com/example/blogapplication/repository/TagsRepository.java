package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagsRepository  extends JpaRepository<Tag,Integer> {
    @Query(value = "select name from tag where name = :name limit 1",nativeQuery = true)
    Optional<String> getName(@Param("name")String name);
}
