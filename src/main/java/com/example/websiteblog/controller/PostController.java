package com.example.websiteblog.controller;

import com.example.websiteblog.model.Comment;
import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.ICommentService;
import com.example.websiteblog.service.IPostService;
import com.example.websiteblog.service.IUserService;
import com.example.websiteblog.utils.RoleConstant.RoleConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
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
    public String getPost(@PathVariable Long id, Model model, HttpServletRequest request){
//        String authUsername = "anonymousUser";
//        if (principal != null) {
//            authUsername = principal.getName();
//        }
        Post currentPost = iPostService.getById(id);
        User postOwner = iUserService.findUserById((long) currentPost.getUserId());
        List<Comment> commentList = iCommentService.getListComment(id);
       // System.out.println(currentPost);
        model.addAttribute("currentPost", currentPost);
        model.addAttribute("listCommentCurrentPost", commentList);
        //User userComment = iUserService.findUserActive();
        User currentUser = (User) request.getSession().getAttribute("currentUser");
//        if(userComment != null)
//            model.addAttribute("currentUser", userComment);
        System.out.println("role " + RoleConstant.RoleUser.ROLE_ADMIN.name());
       // System.out.println("role hien tai " + userComment.getRole());
        System.out.println(currentUser);
        if(currentUser != null)
        {
            if (currentPost.getUserId() == currentUser.getId() || currentUser.getRole().equalsIgnoreCase(RoleConstant.RoleUser.ROLE_ADMIN.name())) {
                System.out.println("co quyen edit");
                model.addAttribute("roleEdit", true);
                model.addAttribute("currentUser", currentUser);
                model.addAttribute("postOwner", postOwner);
                return "postDetail";

            }
        }
        //System.out.println(commentList.size());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roleEdit" , false);
        model.addAttribute("postOwner", postOwner);
        return "postDetail";
    }

    @GetMapping("/createNewPost")
    public String createNewPost(Model model, HttpServletRequest request) {
        User userComment = (User) request.getSession().getAttribute("currentUser");
        Post postBlog = new Post();
        postBlog.setId(0L);
        model.addAttribute("postBlog", postBlog);
        model.addAttribute("postIdEdit", null);
        model.addAttribute("currentUser", userComment);
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
    public  String createNewPostSave(Model model, @RequestParam String title, @RequestParam String img, @RequestParam String contentPost, @RequestParam Long id, HttpServletRequest request) {
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
        System.out.println("id pót " + id );
        //Long id = (Long) model.getAttribute("postIdEdit");
        //System.out.println("id " + model.getAttribute("postIdEdit"));
        if(id > 0){
            postBlog.setId(id);
        }
        iPostService.save(postBlog,request);
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




    @GetMapping("editPost/{id}")
    public String editPost(@PathVariable Long id, Model model, HttpServletRequest request) {
        Post post = iPostService.getById(id);
        User userComment = (User) request.getSession().getAttribute("currentUser");
        System.out.println("post can cap nhat : " + id);
        post.setId(id);
        model.addAttribute("postBlog", post);
        model.addAttribute("postIdEdit", id);
        model.addAttribute("currentUser", userComment);
        System.out.println("id " + model.getAttribute("postIdEdit"));
        return "formPost";
    }


    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id) {
        iPostService.delete(id);
        return "redirect:/";
    }


    @GetMapping("/filter")
    public String filterPost(Model model, HttpServletRequest request) {
        List<Post> posts = new ArrayList<>();
        User currentUser= (User) request.getSession().getAttribute("currentUser");
        model.addAttribute("postsFilter", posts);
        model.addAttribute("currentUser", currentUser);
        return "filterPost";
    }

    @PostMapping("/filter")
    public String resFilterPost(@RequestParam String queryString, @RequestParam String sort, Model model, HttpServletRequest request){
        List<Post> posts = iPostService.getFilterPost(queryString,sort);
        User currentUser= (User) request.getSession().getAttribute("currentUser");
        model.addAttribute("postsFilter", posts);
        model.addAttribute("currentUser", currentUser);
        return "filterPost";
    }
}
