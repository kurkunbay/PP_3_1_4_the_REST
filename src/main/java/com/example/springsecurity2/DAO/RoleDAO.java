package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findRolesByName (String name);
    Set<Role> findRolesByNameIn(List<String> names);
}
