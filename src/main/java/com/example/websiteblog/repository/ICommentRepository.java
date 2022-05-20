package com.example.websiteblog.repository;

import com.example.websiteblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICommentRepository {
    public List<Comment> getListComment(Long id);
}
