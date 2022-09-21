package com.example.springsecurity2.service;

import com.example.springsecurity2.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void save(Role role);

    Role findById(Long id);

    Role findByName(String name);

    Set<Role> findRolesSetByName(List<String> names);

    List<Role> findAllRoles();
}
