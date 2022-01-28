package com.learnproj.sheduler.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    @NotNull
    int personId;

    @NotNull
    @Size(min = 1)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(min = 2)
    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "STUDY_GROUP_ID")
    private StudyGroup studyGroup;



}
