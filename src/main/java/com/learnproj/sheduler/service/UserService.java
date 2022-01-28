package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional
    boolean userExists(String username);

    @Transactional
    void save(User user);

    @Transactional
    User get(Long id);

    @Transactional
    List<User> getList();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
