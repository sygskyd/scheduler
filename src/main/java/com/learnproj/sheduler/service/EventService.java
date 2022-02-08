package com.learnproj.sheduler.service;

import com.learnproj.sheduler.DTO.EventDTO;
import com.learnproj.sheduler.model.Event;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    @Transactional
    List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);

    @Transactional
    List<Event> findAll();

    @Transactional
    Event save(Event event);

    @Transactional
    Event delete(Event event);

    Event fromDTO(EventDTO eventDTO);
}
