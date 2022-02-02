package com.learnproj.sheduler.controller;


import com.learnproj.sheduler.model.Role;
import com.learnproj.sheduler.model.User;
import com.learnproj.sheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/auth")
    public String signInPage(Model theModel, String error, String logout) {
        if (error != null) {
            theModel.addAttribute("error", true);
        }
        if (logout != null) {
            theModel.addAttribute("logout", true);
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model theModel, String error) {
        if (error != null) {
            theModel.addAttribute("error", true);
        }
        User user = new User();
        theModel.addAttribute("user", user);
        return "registration";
    }

    //TODO
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") @Valid User user,
                               BindingResult result) {
        if (userService.existsByUsername(user.getUsername())) {
           result.rejectValue("user",null, "user already exist!");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setRole(Role.USER);
        userService.save(newUser);
        return "login";
    }
}


