package com.learnproj.sheduler.repository;

import com.learnproj.sheduler.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Integer>{
}
