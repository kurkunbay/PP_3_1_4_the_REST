package com.example.springsecurity2.service;


import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.DAO.UserDAO;
import com.example.springsecurity2.model.Role;
import com.example.springsecurity2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleService roleService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void addDefaultUserAndRolesIfNotExist() {
        if ((roleService.findById(1L) == null)
                || (roleService.findById(2L) == null)) {
            roleService.save(new Role(1L, "ROLE_ADMIN"));
            roleService.save(new Role(2L, "ROLE_USER"));
        }
        if (userDAO.findDistinctByUsername("t@t") == null) {
            var userAdmin = new User();
            userAdmin.setEmail("t@t");
            userAdmin.setPassword(bCryptPasswordEncoder.encode("qqq"));
            userAdmin.setFirstname("admin");
            userAdmin.setLastname("admin");
            userAdmin.setAge(11);
            List<String> role = Collections.singletonList("ROLE_ADMIN");
            userAdmin.setRoles(roleService.findRolesSetByName(role));
            userDAO.save(userAdmin);
        }

    }

    @Transactional
    @Override
    public void saveUser(User user) {

        if (userDAO.findDistinctByUsername(user.getUsername()) == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDAO.save(user);
        } else {
            try {
                throw new Exception("User exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (user.getId() == null) {
            try {
                throw new Exception("User not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!user.getPassword().equals("") || user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDAO.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long id) {
        return userDAO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByEmail(String name) {
        var userFindDB = userDAO.findDistinctByUsername(name);
        if (userFindDB == null) {
            throw new UsernameNotFoundException("User not exist");
        }
        return userFindDB;
    }


    @Transactional
    @Override
    public void removeUser(long id) {
        userDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll(Sort.by(Sort.Direction.ASC, "firstname"));
    }

}