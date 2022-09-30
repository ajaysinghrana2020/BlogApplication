package com.example.blogapplication.service;

import com.example.blogapplication.model.entities.Post;
import com.example.blogapplication.repository.PostsRepository;
import com.example.blogapplication.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {
    @Autowired
    PostsRepository postsRepository;

    @Autowired
    TagsRepository tagsRepository;

    public List<Post> getListOfPosts(String query) {
        return postsRepository.searchPost(query);
    }

    public Page<Post> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 2);
        return postsRepository.findAll(pageable);
    }

    public Post getOnlyOne(Integer id) {
        Post post = postsRepository.findById(id).get();
        return post;
    }

    public void save(Post post) {

        postsRepository.save(post);
    }

    public void delete(Integer id) {
        postsRepository.deleteById(id);
    }
}