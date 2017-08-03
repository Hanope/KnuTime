package com.knutime.service.timetable;

import com.knutime.domain.CurrentUser;
import com.knutime.domain.Timetable;
import com.knutime.domain.User;
import com.knutime.repository.TimetableRepository;
import com.knutime.repository.UserRepository;
import com.knutime.service.course.CourseService;
import com.knutime.service.user.UserService;
import com.knutime.util.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
    private final UserRepository userRepository;
    private final TimetableRepository timetableRepository;
    private final UserService userService;

    @Autowired
    public TimetableServiceImpl(UserRepository userRepository, TimetableRepository timetableRepository, UserService userService) {
        this.userRepository = userRepository;
        this.timetableRepository = timetableRepository;
        this.userService = userService;
    }

    @Override
    public Timetable createTimetable(Long userId, Timetable tableForm) {
        LOGGER.debug("Creating new TimeTable");
        User user = userRepository.findOne(userId);
        Timetable timetable = new Timetable();
        String serialNumber = Encode.md5(user.getEmail() + System.currentTimeMillis());

        timetable.setUser(user);
        timetable.setName(tableForm.getName());
        timetable.setSemester(tableForm.getSemester());
        timetable.setSerialNumber(serialNumber);

        // reload currentUser
        user.addTimetable(timetable);
        userService.reloadCurrentUser(user);

        return timetableRepository.save(timetable);
    }

    @Override
    public Timetable getTimetable(String serialNumber) {
        return timetableRepository.findOneBySerialNumber(serialNumber);
    }
}
