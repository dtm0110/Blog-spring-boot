package com.example.websiteblog.service.impl;

import com.example.websiteblog.model.User;
import com.example.websiteblog.repository.IUserRepository;
import com.example.websiteblog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService implements IUserService {
    @Autowired
    private final IUserRepository iUserRepository;
    @Override
    public User findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        return iUserRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return iUserRepository.getAllUser();
    }

    @Override
    public void save(User user) {

        iUserRepository.save(user);
    }

    @Override
    public User findUserActive() {
        return iUserRepository.findUserActive();
    }

    @Override
    public List<User> getSearchUser(String queryString, String sort) {
        return iUserRepository.getSearchUser(queryString,sort);
    }

    @Override
    public User login(String username, String password) {
        return iUserRepository.login(username,password);
    }

    @Override
    public void delete(Long id) {
        iUserRepository.delete(id);
    }

    @Override
    public void unban(Long id) {
        iUserRepository.unban(id);
    }
}
