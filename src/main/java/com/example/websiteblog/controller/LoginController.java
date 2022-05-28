package com.example.websiteblog.controller;

import com.example.websiteblog.model.User;
import com.example.websiteblog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LoginController {

    private final IUserService iUserService;
    @GetMapping("/login")
    public String login(Principal principal, Model model, HttpServletRequest request) {
        User userSecssion = (User) request.getSession().getAttribute("User");
        //session
        if(userSecssion == null)
            return "login";
        model.addAttribute("User", userSecssion);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpServletRequest request) {
        model.addAttribute("logining", false);
        //session
        request.getSession().invalidate();
        return "login";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request){
        User userLogin = iUserService.login(username, password);
        System.out.println(userLogin);
        if(userLogin == null){
            System.out.println("abcxyz");
            model.addAttribute("errorLogin", true);
            model.addAttribute("logining", true);
            return "login";
        }
        //create session
        request.getSession().setAttribute("User", userLogin);
//        request.getSession().setMaxInactiveInterval();
        model.addAttribute("errorLogin", false);
        return "redirect:/";
    }
}
