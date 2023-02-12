package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.role.RoleServiceInterface;
import ru.kata.spring.boot_security.demo.service.user.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final UserServiceImp userService;
    private final RoleServiceInterface roleService;

    @Autowired
    public UserResource(UserServiceImp userService,
                        RoleServiceInterface roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId, Model model) {
        List<Role> allRoles = roleService.allRoles();
        model.addAttribute("allRoles", allRoles);
        return userService.findUserById(userId);

    }

    @PostMapping("/{userId}")
    public void create(@RequestBody User user) {
        userService.saveUser(user);
    }
}
