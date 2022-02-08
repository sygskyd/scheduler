package com.learnproj.sheduler.repository;

import com.learnproj.sheduler.model.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository <StudyGroup, Integer> {

    Optional<StudyGroup> findByGroupName(String groupName);

}
