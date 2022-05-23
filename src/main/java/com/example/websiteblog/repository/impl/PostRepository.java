package com.example.websiteblog.repository.impl;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostRepository implements IPostRepository {
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Post> getAllPost() {
        String sql = "select * from post";
        List<Post> listPost = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Post.class));
        return listPost;
    }

    @Override
    public Post getById(Long id) {
        String sql = "select * from post where id = ?";
        return (Post) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Post.class));
    }

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO `sys`.`post` (user_id, title, img, content_post) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getUserId(), post.getTitle(), post.getImg(), post.getContentPost() );
    }
}
