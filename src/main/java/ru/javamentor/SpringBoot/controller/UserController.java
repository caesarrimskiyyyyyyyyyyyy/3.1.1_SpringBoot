package ru.javamentor.SpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.SpringBoot.model.User;
import ru.javamentor.SpringBoot.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getShowAll(Model model) {
        model.addAttribute("users", userService.showAll());
        return "user/users";
    }

    @GetMapping("/new")
    public String getCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") @Valid User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String getEditUserForm(@RequestParam("id") Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
