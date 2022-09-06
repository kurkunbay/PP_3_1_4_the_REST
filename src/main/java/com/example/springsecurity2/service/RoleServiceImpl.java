package com.example.springsecurity2.service;

import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        roleDAO.deleteRoleById(id);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    @Transactional
    public Role getByRoleName(String roleName) {
        return roleDAO.getByRoleName(roleName);
    }
}
