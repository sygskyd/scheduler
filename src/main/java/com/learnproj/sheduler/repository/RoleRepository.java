package com.learnproj.sheduler.repository;

import java.util.Optional;

import com.learnproj.sheduler.model.ERole;
import com.learnproj.sheduler.model.Role;
import com.learnproj.sheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
