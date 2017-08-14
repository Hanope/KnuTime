package com.knutime.service.timetable;

import com.knutime.domain.timetable.Timetable;

import java.util.Map;

public interface TimetableService {

    Timetable createTimetable(Long userId, Timetable tableForm);

    boolean isExistsTimetable(String serialNumber);

    Timetable getTimetable(String serialNumber);

    Map<String, Object> deleteCourse(String tableId, Long courseId);

    Map<String, Object> addCourse(String tableId, Long courseId);
}
