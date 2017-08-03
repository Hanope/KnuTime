package com.knutime.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "course_info")
public class CourseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "goals", nullable = true)
    private String goals;

    @Column(name = "textbook", nullable = true)
    private String textbook;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "criteria", nullable = true)
    private String criteria;

    @Column(name = "notice", nullable = true)
    private String notice;

    @Column(name = "disabilities", nullable = true)
    private String disabilities;

    @Override
    public String toString() {
        return "CourseInfo{" +
                "goals=" + goals +
                ", textbook=" + textbook +
                ", description='" + description +
                ", criteria=" + criteria +
                ", notice=" + notice +
                ", disabilities=" + disabilities +
                '}';
    }
}
