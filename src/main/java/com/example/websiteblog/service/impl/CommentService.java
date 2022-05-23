package com.example.websiteblog.service.impl;

import com.example.websiteblog.model.Comment;
import com.example.websiteblog.repository.ICommentRepository;
import com.example.websiteblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository iCommentRepository;
    @Override
    public List<Comment> getListComment(Long id) {

        return iCommentRepository.getListComment(id);
    }

    @Override
    public void save(Comment comment) {
        iCommentRepository.save(comment);
    }
}
