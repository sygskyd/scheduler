package com.learnproj.sheduler.security;

import com.learnproj.sheduler.model.Role;
import com.learnproj.sheduler.model.User;
import com.learnproj.sheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DefaultDataConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;


    private void createDefaultUsers() {
//        User newManager = new User();
//        newManager.setUsername("manager");
//        newManager.setPassword(encoder.encode("manager"));
//        newManager.setEmail("manager@unknown.org");
//        newManager.setRole(Role.MANAGER);
//        userService.save(newManager);
    }

}

