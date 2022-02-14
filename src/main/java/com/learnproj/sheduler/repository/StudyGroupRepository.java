package com.learnproj.sheduler.repository;

import com.learnproj.sheduler.model.StudyGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository <StudyGroup, Integer> {

    @EntityGraph(attributePaths = {"personList"})
    Page<StudyGroup> findAll(Pageable pageable);

    Optional<StudyGroup> findByGroupName(String groupName);

}
