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

import java.util.List;

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
    public List<User> getSearchUser(String queryString, String sort) {
        String sql = "select * from `sys`.`user` where `user_name` LIKE" + " '%" +queryString+ "%'" +  " order by `user_name` " + sort;
        try {
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from user where id = ?";
        Object[] user = new Object[] {id};
        String sql = "update user set deactive = ? where id = ? ";
        jdbcTemplate.update(sql, 1 , id);
        // Delete
        //jdbcTemplate.update(query, user);
    }

    @Override
    public void unban(Long id) {
        String query = "update from user where id = ?";
        Object[] user = new Object[] {id};
        String sql = "update user set deactive = ? where id = ? ";
        jdbcTemplate.update(sql, 0 , id);
    }

    @Override
    public User findUserById(Long id) {
        try {
            String sql = "select * from user where id = ?";
            return (User) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
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

//    @Override
//    public User findUserActive() {
//        try {
//            String sql = "select * from user where is_active = ?";
//            return (User) jdbcTemplate.queryForObject(
//                    sql,
//                    new Object[]{1},
//                    new BeanPropertyRowMapper(User.class));
//        }
//        catch (Exception e){
//            return null;
//        }
//    }

    @Override
    public List<User> getAllUser() {
        try {
            String sql = "select * from user";
            List<User> listUser = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper(User.class));
            return listUser;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public User login(String username, String password) {
        try {
            String sql = "select * from user where user_name = ? and password = ? and deactive = ?";
            return (User) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{username, password, 0},
                    new BeanPropertyRowMapper(User.class));
        }
        catch (Exception e){
            return null;
        }
    }
}
