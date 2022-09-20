package com.example.blogapplication.service;

import com.example.blogapplication.model.entities.Post;
import com.example.blogapplication.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    PostsRepository postsRepository;

    public List<Post> getListOfPosts() {
        List<Post> listOfPosts = postsRepository.findAll();
        return listOfPosts;
    }

    public Post getOnlyOne(Integer id) {
        Post post = postsRepository.findById(id).get();
        return post;
    }

    public void save(Post post) {

        postsRepository.save(post);
    }
}