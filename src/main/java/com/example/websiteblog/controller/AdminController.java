package com.example.websiteblog.controller;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.IPostService;
import com.example.websiteblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    public IUserService iUserService;

    @GetMapping("/manage")
    public String manageUser(Model model){
        List<User> users = iUserService.getAllUser();
        model.addAttribute("users", users);
        return "manage";
    }

    @GetMapping("/user/{id}")
    public String userDetail(Model model, @PathVariable Long id){
        User user = iUserService.findUserById(id);
        model.addAttribute("currentUserDetail", user);
        return "userDetail";
    }

    @GetMapping("/banUser/{id}")
    public String banUser(Model model, @PathVariable Long id){
        iUserService.delete(id);
        return "redirect:/manage";
    }
}
