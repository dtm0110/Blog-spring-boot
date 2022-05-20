package com.example.websiteblog.service;

import com.example.websiteblog.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface IPostService {
    Optional<Post> getById(Long id);

    Collection<Post> getAll();

    Post save(Post post);

    void delete(Post post);
}
