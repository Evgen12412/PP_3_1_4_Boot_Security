package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

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

    @GetMapping("/admin/get/{userId}")
    public String  getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergetList(userId));
        return "admin";
    }
    @GetMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("user") User user) {

        return "user-info";
    }

    @PostMapping("/addNewUser")
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "user-edit";
    }
     @PatchMapping("editUser/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
