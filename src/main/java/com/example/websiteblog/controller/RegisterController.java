package com.example.websiteblog.controller;


import com.example.websiteblog.model.User;
import com.example.websiteblog.service.IUserService;
import com.example.websiteblog.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, SessionStatus sessionStatus) throws RoleNotFoundException {
        System.out.println("new user: " + user);
        iUserService.save(user);
//        if (iUserService.findByUsername(user.getUsername())!=null) {
//            FieldError usernameTakenError = new FieldError("blogUser","username","Username is already registered try other one or go away");
//            bindingResult.addError(usernameTakenError);
//            bindingResult.rejectValue("username", "error.username","Username is already registered try other one or go away");
//            System.err.println("Username already taken error message");
//        }
//
////        // Validate users fields
//        if (bindingResult.hasErrors()) {
//            System.err.println("New user did not validate");
//            return "registerForm";
//        }
////        // Persist new blog user
////        this.blogUserService.saveNewBlogUser(user);
////        // Create Authentication token and login after registering new blog user
////        Authentication auth = new UsernamePasswordAuthenticationToken(blogUser, blogUser.getPassword(), blogUser.getAuthorities());
////        System.err.println("AuthToken: " + auth);  // for testing debugging purposes
////        SecurityContextHolder.getContext().setAuthentication(auth);
////        System.err.println("SecurityContext Principal: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());  // for testing debugging purposes
////        sessionStatus.setComplete();

        return "redirect:/";
    }
}
