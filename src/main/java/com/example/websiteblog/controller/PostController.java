package com.example.websiteblog.controller;

import com.example.websiteblog.model.Comment;
import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.ICommentService;
import com.example.websiteblog.service.IPostService;
import com.example.websiteblog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
//        Post postBlog = new Post();
//        model.addAttribute("postBlog", postBlog);
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
//    @RequestMapping(value = "/createNewPost", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public  String createNewPostSave(@RequestParam String title, @RequestParam String img, @RequestParam String contentPost) {
//        System.err.println("POST post: " + postBlog); // for testing debugging purposes
//        if (bindingResult.hasErrors()) {
//            System.err.println("Post did not validate");
//            return "formPost";
//        }
        // Save post if all good
        Post postBlog = new Post();
        postBlog.setContentPost(contentPost);
        postBlog.setTitle(title);
        postBlog.setImg(img);
        iPostService.save(postBlog);
        //sessionStatus.setComplete();
        //return "redirect:/post/" + postBlog.getId();
        return "redirect:/";
    }


//    @PostMapping("/createNewPost")
//    public String createNewPost(@Valid @ModelAttribute Post postBlog, BindingResult bindingResult, SessionStatus sessionStatus) {
//        System.err.println("POST post: " + postBlog); // for testing debugging purposes
////        if (bindingResult.hasErrors()) {
////            System.err.println("Post did not validate");
////            return "postForm";
////        }
//        // Save post if all good
//        //
//        iPostService.save(postBlog);
//        System.err.println("SAVE post: " + postBlog); // for testing debugging purposes
//        sessionStatus.setComplete();
//        return "redirect:/post/" + postBlog.getId();
//    }
}
