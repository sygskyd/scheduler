package com.learnproj.sheduler.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude="personList")
@ToString(exclude = "personList")
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

    @ManyToMany(mappedBy = "groupList")
    private List<CalendarDate> calendarDateList;

    @OneToMany(mappedBy = "studyGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> personList;

}
