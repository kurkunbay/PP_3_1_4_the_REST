package com.example.springsecurity2.service;

import com.example.springsecurity2.DAO.RoleDAO;
import com.example.springsecurity2.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Transactional
    @Override
    public Role findById(Long id) {
        Role role = null;
        Optional<Role> optional = roleDAO.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    @Transactional
    @Override
    public Role findByName(String name) {
        return roleDAO.findRolesByName(name);
    }

    @Transactional
    @Override
    public List<Role> findAllRoles() {
        return roleDAO.findAll();
    }

    @Transactional
    @Override
    public Set<Role> findRolesSetByName(List<String> names) {
        return roleDAO.findRolesByNameIn(names);
    }
}
