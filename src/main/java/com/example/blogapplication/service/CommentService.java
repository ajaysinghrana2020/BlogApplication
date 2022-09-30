package com.example.blogapplication.service;

import com.example.blogapplication.model.entities.Comment;
import com.example.blogapplication.model.entities.Post;
import com.example.blogapplication.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getListOfComments() {
        List<Comment> listOfComments = commentRepository.findAll();

        return listOfComments;
    }

    public void saveComment(Comment comments) {

        commentRepository.save(comments);
    }

    public Comment getOnlyOne(Integer id) {
        Comment comment = commentRepository.findById(id).get();
        return comment;
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }


}
