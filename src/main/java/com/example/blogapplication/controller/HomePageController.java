package com.example.blogapplication.controller;

import com.example.blogapplication.model.entities.Comment;
import com.example.blogapplication.model.entities.Post;
import com.example.blogapplication.model.entities.Tag;
import com.example.blogapplication.repository.CommentRepository;
import com.example.blogapplication.service.CommentService;
import com.example.blogapplication.service.PostsService;
import com.example.blogapplication.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    PostsService postsService;

    @Autowired
    TagsService tagsService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/")
    public String homePage(Model model, @Param("query") String query) {
//        List<Post> page = postsService.getListOfPosts(query);
        List<Post> listOfPosts = postsService.getListOfPosts(query);
        model.addAttribute("listOfPosts", listOfPosts);

        List<Tag> allOfTags =tagsService.allTags();
        model.addAttribute("allOfTags",allOfTags);
        return "homepage";
    }

    @RequestMapping("/write")
    public String writeBlog() {
        return "createblog";
    }

    @PostMapping("/createblog")
    public String saveBlog(HttpServletRequest request, Model model) {
        Post post = new Post();
        post.setTitle(request.getParameter("title"));
        post.setExcerpt(request.getParameter("content").substring(0, request.getParameter("content").length() / 10) + "...");
        post.setAuthor(request.getParameter("author"));
        post.setContent(request.getParameter("content"));
        post.setPublishedAt(LocalDateTime.now());
        post.setIsPublished(true);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        String http = request.getParameter("tag");
        List<String> tagsName = List.of(http.toLowerCase().split(","));

        List<String> tagName = new ArrayList<>();
        for (String tag : tagsName) {
            tagName.add(tag.trim());
        }

        List<Tag> listOfTags = new ArrayList<>();

        for (String tag : tagName) {
            Tag tags = new Tag();

            tags.setName(tag);
            tags.setCreatedAt(LocalDateTime.now());
            tags.setUpdatedAt(LocalDateTime.now());
            tags.setPost((List.of(post)));
            listOfTags.add(tags);
        }
        post.setTag(listOfTags);
        tagsService.insertTags(listOfTags);
        postsService.save(post);
        return "redirect:/";
    }

    @GetMapping("/view/{id}")
    public String showPost(@PathVariable Integer id, HttpServletRequest request, Model model) {
        Post post = postsService.getOnlyOne(id);
        model.addAttribute("listOfPosts", post);

        List<Comment> listOfComments = post.getComment();
        model.addAttribute("listOfComments", listOfComments);
        return "view";
    }

    @PostMapping("/comment/{id}")
    public String addComment(@PathVariable Integer id, HttpServletRequest request, Model model) {
        Comment comments = new Comment();

        comments.setName(request.getParameter("name"));
        comments.setEmail(request.getParameter("email"));
        comments.setComment(request.getParameter("comment"));
        comments.setPost(postsService.getOnlyOne(Integer.parseInt(request.getParameter("laura"))));
        comments.setCreatedAt(LocalDateTime.now());
        comments.setUpdatedAt(LocalDateTime.now());

        commentService.saveComment(comments);

        int ids = comments.getPost().getId();
        return "redirect:/view/" + ids;
    }

    @RequestMapping("/delete/{id}")
    public String deletePost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("delete"));

        postsService.delete(id);
        return "redirect:/";

    }

    @GetMapping("/update/{id}")
    public String updatePost(@PathVariable Integer id, HttpServletRequest request, Model model) {
        Post post = postsService.getOnlyOne(id);
        model.addAttribute("post", post);
        return "updatepost";
    }

    @PostMapping("/updates/{id}")
    public String greetingForm(@PathVariable Integer id, HttpServletRequest request, Model model) {
        Post post = postsService.getOnlyOne(id);
        System.out.println("IRON MAN");

        post.setTitle(request.getParameter("title"));
        System.out.println(post.getTitle());
        post.setExcerpt(request.getParameter("content").substring(0, request.getParameter("content").length() / 10) + "...");
        post.setAuthor(request.getParameter("author"));
        post.setContent(request.getParameter("content"));
        post.setPublishedAt(LocalDateTime.now());
        post.setIsPublished(true);
        post.setUpdatedAt(LocalDateTime.now());

        String http = request.getParameter("tag");
        List<String> tagsName = List.of(http.toLowerCase().split(","));
        List<String> tagName = new ArrayList<>();
        for (String tag : tagsName) {
            tagName.add(tag.trim());
        }
        System.out.println(tagName);
        List<Tag> listOfTags = new ArrayList<>();

        for (String tag : tagName) {
            Tag tags = new Tag();

            tags.setName(tag);

            tags.setUpdatedAt(LocalDateTime.now());
            listOfTags.add(tags);
        }
        post.setTag(listOfTags);
        System.out.println(listOfTags);
        tagsService.insertTags(listOfTags);

        postsService.save(post);
//            return "redirect:/view?laura"+id;
        return "redirect:/";
    }

    @PostMapping("/deletecomment/{id}")
    public String deleteComment(@PathVariable Integer id, HttpServletRequest request, Model model) {
        Comment comments = commentRepository.findById(id).get();
        int post_id = comments.getPost().getId();
        commentService.delete(id);
        return "redirect:/view/" + post_id;
    }

    @GetMapping("/editcomment/{id}")
    public String editComment(@PathVariable Integer id, HttpServletRequest request, Model model) {
        Comment comments = commentRepository.findById(id).get();
        int post_id = comments.getPost().getId();
        model.addAttribute("comments", comments);
        return "updatecomment";
    }

    @PostMapping("/updatescomment/{id}")
    public String updateComment(@PathVariable Integer id, HttpServletRequest request) {
        Comment comment = commentRepository.findById(id).get();
        comment.setName(request.getParameter("name"));
        comment.setEmail(request.getParameter("email"));
        comment.setComment(request.getParameter("comment"));
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        int post_id = comment.getPost().getId();
        return "redirect:/view/" + post_id;
    }




}
