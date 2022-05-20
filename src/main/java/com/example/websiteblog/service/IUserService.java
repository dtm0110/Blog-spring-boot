package com.example.websiteblog.service;

import com.example.websiteblog.model.User;

public interface IUserService {
    public User findByUsername(String username);
    public void save(User user);
}
