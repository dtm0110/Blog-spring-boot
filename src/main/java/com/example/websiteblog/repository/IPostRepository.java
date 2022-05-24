package com.example.websiteblog.repository;

import com.example.websiteblog.model.Post;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPostRepository {
    public List<Post> getAllPost();
    public Post getById(Long id);
}