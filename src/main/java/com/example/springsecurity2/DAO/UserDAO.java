package com.example.springsecurity2.DAO;


import com.example.springsecurity2.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getUserByEmail(String email);
}
