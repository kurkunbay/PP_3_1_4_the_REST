package com.example.springsecurity2.controller;


import com.example.springsecurity2.model.User;
import com.example.springsecurity2.service.RoleService;
import com.example.springsecurity2.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public String user(Principal principal, Model model) {
        String email = principal.getName();
        User admin = userService.getUserByEmail(email);
        model.addAttribute("admin", admin);
        model.addAttribute("roles", admin.getRoles());
        return "all-users";
    }

    @GetMapping("/all-users")
    public String showAllUsers(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("newUser", new User());
        model.addAttribute("rolesNames", roleService.getAllRoles());
        return "all-users";
    }
//    @GetMapping("/addNewUser")
//    public String addNewUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("rolesNames", roleService.getAllRoles());
//        return "user-info";
//    }

    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "rolesNames") String[] roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin/all-users";
    }
    @GetMapping("/updateInfo/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("rolesNames", roleService.getAllRoles());
        return "user-info";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all-users";
    }
}