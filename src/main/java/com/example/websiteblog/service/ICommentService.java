package com.example.websiteblog.service;

import com.example.websiteblog.model.Comment;

import java.util.List;

public interface ICommentService {
    public List<Comment> getListComment(Long id);
    public void save(Comment comment);
}
