package com.example.websiteblog.repository.impl;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostRepository implements IPostRepository {
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Post> getAllPost() {
        try {
            String sql = "select * from post";
            List<Post> listPost = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper(Post.class));
            return listPost;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Post> getFilterPost(String queryString, String sort) {

        String sql = "select * from post where title LIKE" + "'%" +queryString+ "%'" +  "order by created_time " + sort;
//        Map<String, Object> argMap = new HashMap<>();
//        argMap.put("queryString", queryString);
//        argMap.put("sort", sort);
        try {
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper(Post.class));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Post getById(Long id) {
        try {
            String sql = "select * from post where id = ?";
            return (Post) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper(Post.class));
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void save(Post post) {
        System.out.println("cap nhat post " + post);
        if(post.getId() == null) {
            String sql = "INSERT INTO `sys`.`post` (user_id, title, img, content_post) VALUES(?, ?, ?, ?)";
            jdbcTemplate.update(sql, post.getUserId(), post.getTitle(), post.getImg(), post.getContentPost());
        }
        else{
            System.out.println("cap nhat");
            String sql = "update post set user_id = ?, title= ?, img =?,  content_post = ? where id = ? ";
            jdbcTemplate.update(sql, post.getUserId(), post.getTitle(), post.getImg(), post.getContentPost(), post.getId());
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from post where id = ?";
        Object[] post = new Object[] {id};

        // Delete
        jdbcTemplate.update(query, post);
    }
}
