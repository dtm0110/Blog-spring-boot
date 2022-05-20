package com.example.websiteblog.service.impl;

import com.example.websiteblog.model.User;
import com.example.websiteblog.repository.IUserRepository;
import com.example.websiteblog.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService implements IUserService {
    private final IUserRepository iUserRepository;
    @Override
    public User findByUsername(String username) {

        return iUserRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }
}
