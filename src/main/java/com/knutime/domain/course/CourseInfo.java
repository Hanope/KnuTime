package com.knutime.domain.course;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "goals", nullable = true, columnDefinition = "TEXT")
    private String goals;

    @Column(name = "textbook", nullable = true, columnDefinition = "TEXT")
    private String textbook;

    @Column(name = "description", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "criteria", nullable = true, columnDefinition = "TEXT")
    private String criteria;

    @Column(name = "notice", nullable = true, columnDefinition = "TEXT")
    private String notice;

    @Column(name = "disabilities", nullable = true, columnDefinition = "TEXT")
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
