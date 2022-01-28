package com.learnproj.sheduler.controller;


import com.learnproj.sheduler.model.ERole;
import com.learnproj.sheduler.model.Role;
import com.learnproj.sheduler.model.User;
import com.learnproj.sheduler.repository.RoleRepository;
import com.learnproj.sheduler.repository.UserRepository;
import com.learnproj.sheduler.security.UserDetailsImpl;
import com.learnproj.sheduler.security.jwt.JwtUtils;
import com.learnproj.sheduler.security.jwt.payload.request.LoginRequest;
import com.learnproj.sheduler.security.jwt.payload.response.JwtResponse;
import com.learnproj.sheduler.security.jwt.payload.response.MessageResponse;
import com.learnproj.sheduler.service.RoleService;
import com.learnproj.sheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/login")
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

    @PostMapping("/signin")
    public String authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
        return "/index";
    }


    @PostMapping("/signup")
    public String registerUser(@Valid User user) {
        if (userService.existsByUsername(user.getUsername())) {
           ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
           return "login";
        }

        if (userService.existsByEmail(user.getEmail())) {
            ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
            return "login";
        }

        User newUser = new User(user.getUsername(), user.getEmail(),
                encoder.encode(user.getPassword()));
        Set<Role> strRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.getName().toString()) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "MANAGER":
                        Role modRole = roleRepository.findByName(ERole.MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        newUser.setRoles(roles);
        userService.save(newUser);

        ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        return "login";
    }
}


