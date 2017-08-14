package com.knutime.domain.timetable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knutime.domain.course.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IdClass(CourseTimetableId.class)
@Table(name = "course_timetable")
public class CourseTimetable {
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_course_id")
    private Course course;

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_timetable_id")
    private Timetable timetable;

    public CourseTimetable() {
    }

    public CourseTimetable(Course course, Timetable timetable) {
        this.course = course;
        this.timetable = timetable;
    }
}
