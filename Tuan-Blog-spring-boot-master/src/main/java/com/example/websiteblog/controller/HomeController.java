package com.example.websiteblog.controller;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @Autowired
    public IPostService iPostService;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        model.addAttribute("logining", false);
        model.addAttribute("postIdEdit", null);
    }

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){
        List<Post> posts = iPostService.getAllPost();
        model.addAttribute("posts", posts);
        User userSession = (User) request.getSession().getAttribute("currentUser");
        if(userSession != null)
            model.addAttribute("currentUser", userSession);
//        else
//            model.addAttribute("User", new User());
        return "home";
    }
}
