package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository  extends JpaRepository<Tag,Integer> {

}
