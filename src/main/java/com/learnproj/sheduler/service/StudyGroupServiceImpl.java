package com.learnproj.sheduler.service;

import com.learnproj.sheduler.model.StudyGroup;
import com.learnproj.sheduler.repository.StudyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyGroupServiceImpl implements StudyGroupService {
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupServiceImpl(StudyGroupRepository studyGroupRepository) {
        this.studyGroupRepository = studyGroupRepository;
    }


    @Override
    @Transactional
    public Page<StudyGroup> findAll(int page, int size) {
        return studyGroupRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public List<StudyGroup> findAll() {
        return studyGroupRepository.findAll();
    }

    @Override
    @Transactional
    public StudyGroup findById(int theId) {
        return studyGroupRepository.findById(theId).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + theId));
    }


    @Override
    @Transactional
    public StudyGroup findByGroupName(String groupName) {
        return studyGroupRepository.findByGroupName(groupName).orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
    }

    @Override
    @Transactional
    public void save(StudyGroup studyGroup) {
        studyGroupRepository.save(studyGroup);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        studyGroupRepository.deleteById(theId);
    }
}
