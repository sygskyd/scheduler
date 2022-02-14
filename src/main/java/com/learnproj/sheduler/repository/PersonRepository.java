package com.learnproj.sheduler.repository;

import com.learnproj.sheduler.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository <Person, Integer>{

    @EntityGraph(attributePaths = "studyGroup")
    Page<Person> findAll(Pageable pageable);
}
