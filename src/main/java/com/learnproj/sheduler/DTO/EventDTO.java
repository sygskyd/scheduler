package com.learnproj.sheduler.DTO;


import com.learnproj.sheduler.model.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime finish;
    private String groupName;

    public static EventDTO fromModel(Event event){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setStart(event.getStart());
        eventDTO.setFinish(event.getFinish());
        eventDTO.setGroupName(event.getStudyGroup().getGroupName());
        return eventDTO;
    }
}
