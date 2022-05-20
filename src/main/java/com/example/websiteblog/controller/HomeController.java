package com.example.websiteblog.controller;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.service.IPostService;
import com.example.websiteblog.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {

    @Autowired
    public IPostService iPostService;

    @GetMapping("/")
    public String home(Model model){

        Collection<Post> posts = iPostService.getAll();
        model.addAttribute("posts", posts);
        return "home";
    }
}
