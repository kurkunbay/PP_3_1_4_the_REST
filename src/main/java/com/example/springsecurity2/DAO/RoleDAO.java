package com.example.springsecurity2.DAO;

import com.example.springsecurity2.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRoles();

    void saveRole(Role role);

    void deleteRoleById(Long id);

    Role getRoleById(Long id);

    Role getByRoleName(String roleName);
}
