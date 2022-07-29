package com.example.springsecurity2.service;


import com.example.springsecurity2.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User saveUser(User user);

    void deleteById(Long id);

    User findByEmail(String email);
}
