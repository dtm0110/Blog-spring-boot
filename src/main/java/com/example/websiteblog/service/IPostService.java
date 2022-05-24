package com.example.websiteblog.service;

import com.example.websiteblog.model.Post;

import java.util.List;
import java.util.Map;

public interface IPostService {
    Post getById(Long id);

    public List<Post> getAllPost();

    public List<Post> getFilterPost(String queryString, String sort);
    void save(Post post);

    void delete(Long id);
}
