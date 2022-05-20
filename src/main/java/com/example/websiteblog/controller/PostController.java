package com.example.websiteblog.controller;

import com.example.websiteblog.model.Comment;
import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.ICommentService;
import com.example.websiteblog.service.IPostService;
import com.example.websiteblog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("post")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostController {
    private final IPostService iPostService;
    private final IUserService iUserService;
    private final ICommentService iCommentService;

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal){
//        String authUsername = "anonymousUser";
//        if (principal != null) {
//            authUsername = principal.getName();
//        }
        Post currentPost = iPostService.getById(id);
        List<Comment> commentList = iCommentService.getListComment(id);
       // System.out.println(currentPost);
        model.addAttribute("currentPost", currentPost);
        model.addAttribute("listCommentCurrentPost", commentList);
        //System.out.println(commentList.size());
        return "postDetail";
    }

    @GetMapping("/createNewPost")
    public String createNewPost(Model model, Principal principal) {
        Post post = new Post();
        model.addAttribute("postBlog", post);
        return "formPost";
        // Just curious  what if we get username from Principal instead of SecurityContext
//        String authUsername = "anonymousUser";
//        if (principal != null) {
//            authUsername = principal.getName();
//        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find user by username
       // Optional<User> optionalBlogUser = iUserService.findByUsername(authUsername);
        // set user to post and put former in model
//        if (optionalBlogUser.isPresent()) {
//            Post post = new Post();
//            post.setUser(optionalBlogUser.get());
//            model.addAttribute("post", post);
//            return "postForm";
//        } else {
//            return "error";
//        }

    }

    @PostMapping("/createNewPost")
    public String createNewPost(@Valid @ModelAttribute Post post, BindingResult bindingResult, SessionStatus sessionStatus) {
        System.err.println("POST post: " + post); // for testing debugging purposes
        if (bindingResult.hasErrors()) {
            System.err.println("Post did not validate");
            return "formPost";
        }
        // Save post if all good
        iPostService.save(post);
        System.err.println("SAVE post: " + post); // for testing debugging purposes
        sessionStatus.setComplete();
        return "redirect:/post/" + post.getId();
    }
}
