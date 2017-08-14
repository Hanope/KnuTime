package com.knutime.domain.timetable;

import java.io.Serializable;

public class CourseTimetableId implements Serializable {
    private Long course;
    private Long timetable;

    public CourseTimetableId() { }

    public CourseTimetableId(Long course, Long timetable) {
        this.course = course;
        this.timetable = timetable;
    }
}
