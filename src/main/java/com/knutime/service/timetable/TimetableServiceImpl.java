package com.knutime.service.timetable;

import com.knutime.domain.course.Course;
import com.knutime.domain.timetable.*;
import com.knutime.domain.user.User;
import com.knutime.repository.*;
import com.knutime.service.course.CourseService;
import com.knutime.service.user.UserService;
import com.knutime.util.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableServiceImpl implements TimetableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTimetableRepository courseTimetableRepository;

    @Autowired
    private UserService userService;

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
        LOGGER.debug("Get TimeTable {}", serialNumber);
        return timetableRepository.findOneBySerialNumber(serialNumber);
    }

    @Override
    public boolean isExistsTimetable(String serialNumber) {
        return timetableRepository.existsBySerialNumber(serialNumber);
    }

    @Override
    public int addCourse(String serialNumber, Long courseId, Long userId) {
        Timetable timetable = timetableRepository.findOneBySerialNumber(serialNumber);
        Course course = courseRepository.findOne(courseId);

        if(timetable == null || course == null)
            return BAD_ACCESS;

        if(!timetable.getUser().getId().equals(userId))
            return UNAUTHORIZED;

        if(isDuplicateCourse(timetable, course))
            return EXISTS_COURSE;

        if(isDuplicateTime(timetable, course))
            return EXISTS_TIME;

        CourseTimetable courseTimetable = new CourseTimetable(course, timetable);
        courseTimetableRepository.save(courseTimetable);

        return SUCCESS;
    }

    @Override
    public int deleteCourse(String timetableId, Long courseId, Long userId) {
        Timetable timetable = timetableRepository.findOneBySerialNumber(timetableId);
        CourseTimetable courseTimetable = courseTimetableRepository.findOneByCourse_Id(courseId);

        if(!courseTimetable.getTimetable().getUser().getId().equals(userId))
            return UNAUTHORIZED;

        if(courseTimetable == null)
            return NOT_FOUND;

        CourseTimetableId courseTimetableId = new CourseTimetableId(courseId, timetable.getId());
        courseTimetableRepository.delete(courseTimetableId);

        return SUCCESS;
    }

    private boolean isDuplicateCourse(Timetable timetable, Course course) {
        return courseTimetableRepository.existsCourse(timetable, course);
    }

    private boolean isDuplicateTime(Timetable timetable, Course course) {
        return courseTimetableRepository.existsCourseTime(timetable, course);
    }
}
