package com.example.websiteblog.service;

import com.example.websiteblog.model.Post;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IPostService {
    Post getById(Long id);

    public List<Post> getAllPost();

    Post save(Post post);

    void delete(Post post);
}
