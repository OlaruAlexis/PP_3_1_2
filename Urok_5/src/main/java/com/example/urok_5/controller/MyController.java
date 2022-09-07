package com.example.urok_5.controller;

import com.example.urok_5.model.User;
import com.example.urok_5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MyController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/users")
    public String showAllUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "all-users";
    }

    @RequestMapping("/admin/addNewUser")
    public String form(Model model) {

        model.addAttribute("user", new User());

        return "user-form";
    }

    @RequestMapping("/admin/saveUser")
    public String saveNewUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/admin/users";
    }

    @RequestMapping("/admin/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.getUser(id));

        return "user-form";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
