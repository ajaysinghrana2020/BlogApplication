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
import org.springframework.web.bind.annotation.*;
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
        System.out.println(tagName);
        List<Tag> listOfTags = new ArrayList<>();

        for (String tag : tagName) {
            Tag tags = new Tag();

            tags.setName(tag);
            tags.setCreatedAt(LocalDateTime.now());
            tags.setUpdatedAt(LocalDateTime.now());

            listOfTags.add(tags);
        }
        post.setTag(listOfTags);

        tagsService.insertTags(listOfTags);

        postsService.save(post);
        return "redirect:/";
    }

    @GetMapping("/view")
    public String showPost(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("laura"));
        Post post = postsService.getOnlyOne(id);
        model.addAttribute("listOfPosts", post);
        List<Comment> list= commentService.getListOfComments();
        List<Comment> listOfComments = commentService.getListOfComments();
        int i = 0;
        for (Comment comment : listOfComments) {
            int abc = listOfComments.get(i).getPostId();
            if (post.getId() == abc)
                model.addAttribute("listOfComments", listOfComments);

            i++;
        }


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

    //    @GetMapping("/comment")
//    public String showComments( Model model){
//        List<Comment> listOfComments = commentService.getListOfComments();
//        model.addAttribute("listOfComments", listOfComments);
//        System.out.println(listOfComments);
//        return "view";
//    }
    @RequestMapping ("/delete")
    public String deletePost(HttpServletRequest request) {
        int id= Integer.parseInt(request.getParameter("delete"));

        postsService.delete(id);
        return "homepage";
//        return null;
    }





    @GetMapping ("/update")
    public String updatePost(HttpServletRequest request,Model model){
        int id= Integer.parseInt(request.getParameter("update"));
        Post post = postsService.getOnlyOne(id);
//        System.out.println(post);
        model.addAttribute("post", post);
        return "updatepost";
    }

        @PostMapping("/update/{id}")
        public String greetingForm(@PathVariable Integer id,HttpServletRequest request, Model model) {
//            int id= Integer.parseInt(request.getParameter("update"));
            Post post = postsService.getOnlyOne(id);
            System.out.println("IRON MAN");

            post.setTitle(request.getParameter("title"));
            System.out.println(post.getTitle());
            post.setExcerpt(request.getParameter("content").substring(0, request.getParameter("content").length() / 10) + "...");
            post.setAuthor(request.getParameter("author"));
            post.setContent(request.getParameter("content"));
            post.setPublishedAt(LocalDateTime.now());
            post.setIsPublished(true);
//            post.setCreatedAt(LocalDateTime.now());
            post.setUpdatedAt(LocalDateTime.now());

            String http = request.getParameter("tag");
            List<String> tagsName = List.of(http.toLowerCase().split(","));
            System.out.println(tagsName);

            List<String> tagName = new ArrayList<>();
            for (String tag : tagsName) {
                tagName.add(tag.trim());
            }
            System.out.println(tagName);
            List<Tag> listOfTags = new ArrayList<>();

            for (String tag : tagName) {
                Tag tags = new Tag();

                tags.setName(tag);
//                tags.setCreatedAt(LocalDateTime.now());
                tags.setUpdatedAt(LocalDateTime.now());
                listOfTags.add(tags);
            }
            System.out.println(listOfTags);
            tagsService.insertTags(listOfTags);

            postsService.save(post);
//            return "redirect:/view?laura"+id;
            return "redirect:/";
    }


}




















