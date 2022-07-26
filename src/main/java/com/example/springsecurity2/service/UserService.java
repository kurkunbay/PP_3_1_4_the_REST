package com.example.springsecurity2.service;


import com.example.springsecurity2.model.User;

import java.util.List;

public interface UserService {

    public User findById(Long id);

    public List<User> findAll();

    public User saveUser(User user);

    public void deleteById(Long id);

    User findByEmail(String email);
}
