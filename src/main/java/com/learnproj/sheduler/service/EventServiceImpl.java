package com.learnproj.sheduler.service;

import com.learnproj.sheduler.DTO.EventDTO;
import com.learnproj.sheduler.model.Event;
import com.learnproj.sheduler.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private StudyGroupService studyGroupService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, StudyGroupService studyGroupService) {
        this.eventRepository = eventRepository;
        this.studyGroupService = studyGroupService;
    }

    @Override
    public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end) {
        return eventRepository.findByStartGreaterThanEqualAndFinishLessThanEqual(start, end);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
       return eventRepository.save(event);
    }

    @Override
    public Event delete(Event event) {
        Event deletedEvent = event;
        eventRepository.delete(event);
        return deletedEvent;
    }

    @Override
    public Event fromDTO(EventDTO eventDTO) {
        Event newEvent = new Event();                              //dunno where to put this code, ask someone
        newEvent.setId(eventDTO.getId());
        newEvent.setTitle(eventDTO.getTitle());
        newEvent.setDescription(eventDTO.getDescription());
        newEvent.setStart(eventDTO.getStart());
        newEvent.setFinish(eventDTO.getFinish());
        newEvent.setStudyGroup(studyGroupService.findByGroupName(eventDTO.getGroupName()));
        return newEvent;
    }
}
