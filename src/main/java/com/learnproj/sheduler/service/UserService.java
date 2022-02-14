package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {


    boolean userExists(String username);

    void save(User user);

    User get(Long id);

    List<User> getList();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
