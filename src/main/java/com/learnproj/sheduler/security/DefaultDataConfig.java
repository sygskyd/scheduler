package com.learnproj.sheduler.security;

import com.learnproj.sheduler.model.ERole;
import com.learnproj.sheduler.model.User;
import com.learnproj.sheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DefaultDataConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private void createDefaultUsers() {
        /*User builder if need */
    }

}

