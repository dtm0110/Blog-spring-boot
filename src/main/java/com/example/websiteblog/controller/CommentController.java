package com.example.websiteblog.controller;

import com.example.websiteblog.model.Comment;
import com.example.websiteblog.model.User;
import com.example.websiteblog.service.ICommentService;
import com.example.websiteblog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommentController {

    private final IUserService iUserService;
    private final ICommentService iCommentService;
    @GetMapping("/comment/{id}")
    public String postComment(@PathVariable Long id, Model model){
        System.out.println(id);
        Comment comment = new Comment();
        comment.setPostId(Math.toIntExact(id));
        User userComment = iUserService.findUserActive();
        System.out.println("user active "  + userComment);
        comment.setUserId(Math.toIntExact(userComment.getId()));
        comment.setUserName(userComment.getUserName());
        model.addAttribute("commentPost", comment);
        return "formComment";
    }

    @PostMapping("/comment")
    public String validateComment(@Valid @ModelAttribute Comment commentPost, BindingResult bindingResult) {
        System.err.println("POST comment: " + commentPost); // for testing debugging purposes
        if (bindingResult.hasErrors()) {
            System.err.println("Comment did not validate");
            return "commentForm";
        } else {
            System.out.println("abcxyz " + commentPost);
            iCommentService.save(commentPost);
            System.err.println("SAVE comment: " + commentPost); // for testing debugging purposes
            //sessionStatus.setComplete();
            return "redirect:/post/" + commentPost.getPostId();
        }
    }
}
