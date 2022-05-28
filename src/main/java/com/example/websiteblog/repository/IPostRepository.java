package com.example.websiteblog.repository;

import com.example.websiteblog.model.Post;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface IPostRepository {
    public List<Post> getAllPost();

    public List<Post> getFilterPost(String queryString, String sort);

    public Post getById(Long id);

    public void save(Post post);

    public void delete(Long id);
}
