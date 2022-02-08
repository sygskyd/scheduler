package com.learnproj.sheduler.controller;

import com.learnproj.sheduler.DTO.EventDTO;
import com.learnproj.sheduler.exception.BadDateFormatException;
import com.learnproj.sheduler.model.Event;
import com.learnproj.sheduler.service.EventService;
import com.learnproj.sheduler.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
class EventController {


    private EventService eventService;
    private StudyGroupService studyGroupService;

    @Autowired
    public EventController(EventService eventService, StudyGroupService studyGroupService) {
        this.eventService = eventService;
        this.studyGroupService = studyGroupService;
    }

    @PostMapping("/event")
    public Event addEvent(@RequestBody EventDTO eventDTO) {
        eventDTO.setFinish(eventDTO.getFinish().plusHours(3));    //TODO front timezone maybe later
        eventDTO.setStart(eventDTO.getStart().plusHours(3));
        Event newEvent = eventService.fromDTO(eventDTO);
        return eventService.save(newEvent);
    }

    @PatchMapping("/event")
    public Event updateEvent(@RequestBody EventDTO eventDTO) {
        eventDTO.setFinish(eventDTO.getFinish().plusHours(3));    //TODO front timezone maybe later
        eventDTO.setStart(eventDTO.getStart().plusHours(3));
        Event someEvent = eventService.fromDTO(eventDTO);
        return eventService.save(someEvent);
    }

    @DeleteMapping("/event")
    public ResponseEntity removeEvent(@RequestBody EventDTO eventDTO) {
        Event eventForDelete = eventService.fromDTO(eventDTO);
        eventService.delete(eventForDelete);
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping("/allevents")
    public ResponseEntity getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());

        List<Event> eventList = eventService.findByDateBetween(startDateTime, endDateTime);
        List<EventDTO> eventDTOList = eventList.stream()
                .map(EventDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventDTOList);
    }
}

