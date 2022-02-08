package com.learnproj.sheduler.controller;


import com.learnproj.sheduler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class CalendarController {

    private EventService eventService;

    @Autowired
    public CalendarController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/jsoncalendar")
    public ModelAndView jsoncalendar() {
        return new ModelAndView("/event/jsoncalendar");
    }


}
