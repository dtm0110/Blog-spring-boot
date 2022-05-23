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
        try {
            String sql = "select * from user where user_name = ?";
            return (User) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{username},
                    new BeanPropertyRowMapper(User.class));
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO `sys`.`user` (user_name, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getPassword());
    }

    @Override
    public User findUserActive() {
        try {
            String sql = "select * from user where is_active = ?";
            return (User) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{1},
                    new BeanPropertyRowMapper(User.class));
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public User login(String username, String password) {
        try {
            String sql = "select * from user where user_name = ? and password = ?";
            return (User) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{username, password},
                    new BeanPropertyRowMapper(User.class));
        }
        catch (Exception e){
            return null;
        }
    }


}
