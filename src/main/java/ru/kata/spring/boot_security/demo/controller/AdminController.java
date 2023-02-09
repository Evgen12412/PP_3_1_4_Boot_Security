package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }


    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String  getUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("getUser", user);
        return "user";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("user") User user,Model model) {
        List<Role> allRoles = userService.allRoles();
        model.addAttribute("allRoles", allRoles);
        return "user-info";

    }

    @PostMapping("/addNewUser")
    public String createUser(@ModelAttribute("user") User user) {

        userService.createUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        List<Role> allRoles = userService.allRoles();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("user", userService.findUserById(id));

        return "user-edit";
    }
     @PatchMapping("editUser/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
