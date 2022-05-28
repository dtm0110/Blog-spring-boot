package com.example.websiteblog.service.impl;

import com.example.websiteblog.model.Post;
import com.example.websiteblog.model.User;
import com.example.websiteblog.repository.IPostRepository;
import com.example.websiteblog.repository.IUserRepository;
import com.example.websiteblog.service.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PostService implements IPostService {

    private final IPostRepository iPostRepository;

    private final IUserRepository iUserRepository;
    @Override
    public Post getById(Long id) {
        return iPostRepository.getById(id);
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> allPost = iPostRepository.getAllPost();
        return allPost;
    }

    @Override
    public List<Post> getFilterPost(String queryString, String sort) {
        try{
            return iPostRepository.getFilterPost(queryString, sort);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void save(Post post, HttpServletRequest request) {
        // User userActive = iUserRepository.findUserActive();
        User userActive = (User) request.getSession().getAttribute("currentUser");
//        if(userSession != null)
//            model.addAttribute("currentUser", userSession);
         post.setUserId(Math.toIntExact(userActive.getId()));
        iPostRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        iPostRepository.delete(id);
    }
}
