package com.knutime.service.timetable;

import com.knutime.domain.timetable.Timetable;

public interface TimetableService {

    int BAD_ACCESS = 400;
    int UNAUTHORIZED = 401;
    int NOT_FOUND = 404;
    int EXISTS_COURSE = 100;
    int EXISTS_TIME = 101;
    int SUCCESS = 200;

    Timetable createTimetable(Long userId, Timetable tableForm);

    boolean isExistsTimetable(String serialNumber);

    Timetable getTimetable(String serialNumber);

    int addCourse(String serialNumber, Long courseId, Long userId);

    int deleteCourse(String serialNumber, Long courseId, Long userId);
}
