package com.example.websiteblog.controller;


import com.example.websiteblog.model.User;
import com.example.websiteblog.service.IUserService;
import com.example.websiteblog.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

@Controller
@SessionAttributes("currentUser")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RegisterController {

    private final IUserService iUserService;

    @GetMapping("/signup")
    public String registerForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Errors error) {
        System.out.println("new user: " + user);
        if (iUserService.findByUsername(user.getUserName())!=null) {
            bindingResult.rejectValue("userName", "error.userName","Username is already registered try other one or go away");
            System.err.println("Username already taken error message");
        }
        if (error.hasErrors()) {
            System.err.println("New user did not validate");
            return "signup";
        }
        iUserService.save(user);
        return "redirect:/";
    }
}
