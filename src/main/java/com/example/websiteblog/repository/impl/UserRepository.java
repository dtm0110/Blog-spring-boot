package com.example.websiteblog.repository.impl;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserRepository implements IUserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User findByUsername(String username) {
        String sql = "select * from user where user_name = ?";
        return (User) jdbcTemplate.queryForObject(
                sql,
                new Object[]{username},
                new BeanPropertyRowMapper(User.class));
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO `sys`.`user` (user_name, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
    }
}
