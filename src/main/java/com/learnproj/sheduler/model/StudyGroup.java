package com.learnproj.sheduler.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude= {"personList", "eventList"})
@ToString(exclude = {"personList", "eventList"})
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="groupId")
@Entity
@Table(name = "STUDY_GROUP")
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    @NotNull
    private int groupId;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "PEOPLE_COUNT")
    private int peopleCount;

    @Column(name = "COURSE_NUMBER")
    private int courseNumber;

    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> personList;

    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> eventList;

}
