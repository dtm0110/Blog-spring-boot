package com.example.websiteblog.repository.impl;

import com.example.websiteblog.model.Comment;
import com.example.websiteblog.repository.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommentRepository implements ICommentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Comment> getListComment(Long id) {
        String sql = "SELECT * FROM `sys`.`comment` WHERE `post_id` = :id";
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("id", id);
        try {
                return namedParameterJdbcTemplate.query(sql, argMap, new BeanPropertyRowMapper<>(Comment.class));
            } catch (Exception e) {
                return null;
            }
        }
}
