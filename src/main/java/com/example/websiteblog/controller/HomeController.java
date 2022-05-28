package com.example.websiteblog.controller;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.IPostService;
import com.example.websiteblog.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

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
        //sesssion
        User userSession = (User) request.getSession().getAttribute("User");
//        if(userSession == null)
//            return "login";
        List<Post> posts = iPostService.getAllPost();
        model.addAttribute("posts", posts);
        if(userSession != null)
            model.addAttribute("User", userSession);
        else
            model.addAttribute("User", new User());
        return "home";
    }
}
