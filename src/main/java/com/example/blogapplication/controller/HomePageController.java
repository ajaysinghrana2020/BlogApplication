package com.example.blogapplication.controller;


import com.example.blogapplication.model.entities.Comment;
import com.example.blogapplication.model.entities.Post;
import com.example.blogapplication.model.entities.Tag;
import com.example.blogapplication.service.CommentService;
import com.example.blogapplication.service.PostsService;
import com.example.blogapplication.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    PostsService postsService;

    @Autowired
    TagsService tagsService;

    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Post> listOfPosts = postsService.getListOfPosts();
        model.addAttribute("listOfPosts", listOfPosts);
        for (Post p : listOfPosts) {
        }
        return "homepage";
    }

    @RequestMapping("/write")
    public String writeBlog() {
        return "createblog";
    }

    @PostMapping("/createblog")
    public String saveBlog(HttpServletRequest request, Model model) {
        Post post = new Post();
        Tag tags =new Tag();

        post.setTitle(request.getParameter("title"));
        post.setExcerpt(request.getParameter("content").substring(0, request.getParameter("content").length() / 10) + "...");
        post.setAuthor(request.getParameter("author"));
        post.setContent(request.getParameter("content"));
        post.setPublishedAt(LocalDateTime.now());
        post.setIsPublished(true);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());


        List<String> tagsName = List.of(request.getParameter("tags").toLowerCase().split(","));

        List<String> tagName =new ArrayList<>();
        for(String tag : tagsName) {
            tagName.add(tag.trim());
        }
        List<Tag> tag1 = new ArrayList<>();
        for(String tag : tagName) {
            tags.setName(tag);
            tags.setCreatedAt(LocalDateTime.now());
            tags.setUpdatedAt(LocalDateTime.now());
            tag1.add(tags);

        }
        System.out.println(tag1);






//        tagsService.saveTags(tags);
//        postsService.save(posts);
        return "redirect:/";
    }

    @PostMapping("/view")
    public String showPost(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("laura"));
        Post post = postsService.getOnlyOne(id);
        System.out.println(post);
        model.addAttribute("listOfPosts", post);
        return "view";
    }

//    @PostMapping("/update")
//    public String updatePost(){
//
//    }

    @PostMapping("/createcomment")
    public String addComment(HttpServletRequest request, Model model) {
        Post post = new Post();
        Comment comments = new Comment();
        comments.setName(request.getParameter("name"));
        comments.setEmail(request.getParameter("email"));
        comments.setComment(request.getParameter("comment"));
        comments.setPostId(Integer.parseInt(request.getParameter("laura")));
        comments.setCreatedAt(LocalDateTime.now());
        comments.setUpdatedAt(LocalDateTime.now());

        commentService.saveComment(comments);
//        return "redirect:/view";
        return showPost(request, model);
    }

    }
