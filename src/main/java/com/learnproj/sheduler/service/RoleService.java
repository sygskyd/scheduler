package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.ERole;
import com.learnproj.sheduler.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
