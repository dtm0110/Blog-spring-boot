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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    public IUserService iUserService;

    @GetMapping("/manage")
    public String manageUser(Model model, HttpServletRequest request){
        List<User> users = iUserService.getAllUser();
        User userSession = (User) request.getSession().getAttribute("currentUser");
        model.addAttribute("users", users);
        model.addAttribute("currentUser", userSession);
        return "manage";
    }

    @GetMapping("/user/{id}")
    public String userDetail(Model model, @PathVariable Long id, HttpServletRequest request){
        User user = iUserService.findUserById(id);
        User userSession = (User) request.getSession().getAttribute("currentUser");
        model.addAttribute("currentUser", userSession);
        model.addAttribute("currentUserDetail", user);
        return "userDetail";
    }

    @GetMapping("/banUser/{id}")
    public String banUser(Model model, @PathVariable Long id){
        iUserService.delete(id);
        return "redirect:/manage";
    }

    @GetMapping("/unbanUser/{id}")
    public String unbanUser(Model model, @PathVariable Long id){
        iUserService.unban(id);
        return "redirect:/manage";
    }

//    @GetMapping("/searchUser")
//    public String searchUser(Model model) {
//        List<User> posts = new ArrayList<>();
//        //List<Post> posts = iPostService.getFilterPost("ai","ASC");
//        model.addAttribute("postsFilter", posts);
//        return "filterPost";
//    }

    @PostMapping("/searchUser")
    public String searchUserRes(@RequestParam String queryString, @RequestParam String sort, Model model, HttpServletRequest request){
        List<User> users = iUserService.getSearchUser(queryString,sort);
        //System.out.println(queryString == null);
        User userSession = (User) request.getSession().getAttribute("currentUser");
        model.addAttribute("currentUser", userSession);
        model.addAttribute("users", users);
        return "manage";
    }
}
