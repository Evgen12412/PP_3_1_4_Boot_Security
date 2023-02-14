package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.role.RoleService;
import ru.kata.spring.boot_security.demo.service.user.UserService;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


     @GetMapping
    public String userList(ModelMap model, Principal principal) {
        Optional<User> user =userService.findByUserName(principal.getName());
        List<User> users = userService.showAll();

        model.addAttribute("users", users);
        model.addAttribute("principal",user.get());
        model.addAttribute("newUser",new User());
        model.addAttribute("roles", roleService.showAll());

        return "admin/index";
    }

    @PostMapping
    public String createUser(@ModelAttribute("newUser") User user,@RequestParam("roles") int[] rolesId) {
        List<Role> roles=new ArrayList<>();

        for (int role:rolesId) {
            roles.add(roleService.show(role));
        }
        user.setRoleList(roles);

        userService.create(user);

        return "redirect:/admin";
    }

    @GetMapping("/show")
    public String show( Principal principal,Model model) {
        Optional<User> user =userService.findByUserName(principal.getName());

        model.addAttribute("principal",user.get());

        return "admin/show";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("user") User user,@RequestParam("roles") int[] rolesId) {
        List<Role> roles=new ArrayList<>();

        for (int role:rolesId) {
            roles.add(roleService.show(role));
        }

        user.setPassword(userService.show(id).getPassword());
        user.setRoleList(roles);

        userService.update(id, user);

        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {

        userService.delete(id);

        return "redirect:/admin";
    }
}
