package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleDAO extends JpaRepository<User, Long> {

}