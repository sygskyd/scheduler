//package com.learnproj.sheduler.model;
//
//import lombok.Data;
//import lombok.Getter;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Data
//@Getter
//@Entity
//@Table(name = "CALENDAR")
//public class CalendarDate {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID")
//    @NotNull
//    int calendarId;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotNull
//    @Size(min = 10)
//    @Size(max = 10)
//    @Column(name = "DATE")
//    private Date date;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name = "CALENDAR_STUDYGROUP",
//            joinColumns = @JoinColumn(name = "CALENDAR_ID"),
//            inverseJoinColumns = @JoinColumn(name = "GROUP_ID"))
//    private List<StudyGroup> groupList;
//}
