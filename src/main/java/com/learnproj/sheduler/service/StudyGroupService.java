package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.StudyGroup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudyGroupService {

    Page<StudyGroup> findAll(int page, int size);

    List<StudyGroup> findAll();

    StudyGroup findById(int theId);

    void save(StudyGroup studyGroup);

    void deleteById(int theId);

}
