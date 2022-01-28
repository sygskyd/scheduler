package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonService {

    Page<Person> findAll(int page, int size);

    List<Person> findAll();

    Person findById(int theId);

    void save(Person person);

    void deleteById(int theId);
}
