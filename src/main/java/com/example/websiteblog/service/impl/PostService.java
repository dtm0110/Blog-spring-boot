package com.example.websiteblog.service.impl;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.service.IPostService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Override
    public Optional<Post> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Collection<Post> getAll() {
        return null;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void delete(Post post) {

    }
}
