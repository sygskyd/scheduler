package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PersonService {

    @Transactional
    Page<Person> findAll(int page, int size);

    @Transactional
    List<Person> findAll();

    @Transactional
    Person findById(int theId);

    @Transactional
    void save(Person person);

    @Transactional
    void deleteById(int theId);
}
