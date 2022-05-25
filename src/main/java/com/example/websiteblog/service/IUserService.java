package com.example.websiteblog.service;

import com.example.websiteblog.model.User;

import java.util.List;

public interface IUserService {
    public User findByUsername(String username);

    public User findUserById(Long id);
    public List<User> getAllUser();
    public void save(User user);

    public User findUserActive();

    public List<User> getSearchUser(String queryString, String sort);
    public User login(String username, String password);

    public void delete(Long id);
    public void unban(Long id);
}
