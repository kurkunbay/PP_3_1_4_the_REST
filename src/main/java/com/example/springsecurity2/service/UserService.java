package com.example.springsecurity2.service;


import com.example.springsecurity2.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user, String[] roles);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getUserByEmail(String email);

}