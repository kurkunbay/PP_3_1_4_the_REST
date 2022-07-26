package com.example.springsecurity2.service;


import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.DAO.UserDAO;
import com.example.springsecurity2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    public User findById(Long id) {
        return userDAO.getOne(id);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User saveUser(User user) {
        return userDAO.save(user);
    }

    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User findByEmail(String name) {
        return userDAO.findByEmail(name);
    }

}

