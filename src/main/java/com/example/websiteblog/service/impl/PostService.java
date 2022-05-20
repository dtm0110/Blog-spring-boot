package com.example.websiteblog.service.impl;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.repository.IPostRepository;
import com.example.websiteblog.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostService implements IPostService {

    private final IPostRepository iPostRepository;
    @Override
    public Post getById(Long id) {
        return iPostRepository.getById(id);
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> allPost = iPostRepository.getAllPost();
        return allPost;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void delete(Post post) {

    }
}
