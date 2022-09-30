package com.example.blogapplication.repository;

import com.example.blogapplication.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p join p.tag t where " +
            "lower(p.content) like concat('%',lower(:query),'%')" +
            "OR lower(p.author) like concat('%',lower(:query),'%')" +
            "OR lower(p.title) like concat('%',lower(:query),'%') " +
            "OR lower(t.name) like concat('%',lower(:query),'%')")
    List<Post> searchPost(String query);

    public List<Post> findAllByTagName(String name);
}
