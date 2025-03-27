package com.springboot.dev_spring_boot_demo.controller;

import com.springboot.dev_spring_boot_demo.entity.User;
import com.springboot.dev_spring_boot_demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list-user";
    }

    @GetMapping("/form-insert")
    public String userFormInsert(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form-insert";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "user-form-insert";
        }

        user.setEnabled(true);
        userService.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/form-update")
    public String formUpdate(@RequestParam("username") String username, Model model) {
        User user = userService.findById(username);
        model.addAttribute("user", user);
        return "user-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("username") String username) {
        userService.deleteById(username);
        return "redirect:/user/list";
    }
}
