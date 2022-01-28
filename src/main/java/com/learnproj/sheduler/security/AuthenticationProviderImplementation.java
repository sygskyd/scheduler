//package com.learnproj.sheduler.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthenticationProviderImplementation implements AuthenticationProvider {
//
//
//    private final UserDetailsServiceImplementation userService;
//
//
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthenticationProviderImplementation(UserDetailsServiceImplementation userService, @Lazy PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        if(userService == null) {
//            throw new InternalAuthenticationServiceException("User service is null");
//        }
//
//        UserDetails user = userService.loadUserByUsername(username);
//
//        if(user == null) {
//            throw new AuthenticationCredentialsNotFoundException("No such user was found");
//        }
//
//        if(passwordEncoder.matches(password, user.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
//        } else {
//            throw new AuthenticationServiceException("Unable authenticate user due to some problems");
//        }
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
