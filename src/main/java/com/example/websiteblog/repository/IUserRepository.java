package com.example.websiteblog.repository;

import com.example.websiteblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface IUserRepository {
    public User findByUsername(String username);

    public List<User> getSearchUser(String queryString, String sort);
    public void delete(Long id);

    public void unban(Long id);
    public User findUserById(Long id);
    public void save(User user);

//    public User findUserActive();

    public List<User> getAllUser();

    public User login(String username, String password);
}