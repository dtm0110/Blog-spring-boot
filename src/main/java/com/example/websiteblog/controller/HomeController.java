package com.example.websiteblog.controller;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import org.springframework.web.bind.annotation.ModelAttribute;


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
    public String home(Model model){
        List<Post> posts = iPostService.getAllPost();
        model.addAttribute("posts", posts);
        return "home";
    }
}
