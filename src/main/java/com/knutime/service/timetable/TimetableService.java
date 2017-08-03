package com.knutime.service.timetable;

import com.knutime.domain.Timetable;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

public interface TimetableService {

    Timetable createTimetable(Long userId, Timetable tableForm);

    Timetable getTimetable(String serialNumber);
}
