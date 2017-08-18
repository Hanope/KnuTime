package com.knutime.service.timetable;

import com.knutime.domain.course.Course;
import com.knutime.domain.course.CourseHours;
import com.knutime.domain.timetable.*;
import com.knutime.domain.user.User;
import com.knutime.repository.CourseRepository;
import com.knutime.repository.CourseTimetableRepository;
import com.knutime.repository.TimetableRepository;
import com.knutime.repository.UserRepository;
import com.knutime.service.course.CourseService;
import com.knutime.service.user.UserService;
import com.knutime.util.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if(isDuplicateCourse(course, timetable))
            return EXISTS_COURSE;

        if(isDuplicateTime())
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

//    private int isDuplicateCourse(Timetable timetable, Course course) {
//        Long courseId = course.getId();
//
//        for(CourseTimetable courseTimetable : timetable.getCourseTimetableList()) {
//            if(courseTimetable.getCourse().getId().equals(courseId))
//                return EXISTS_COURSE;
//
//            if(isDuplicateTime(courseTimetable.getCourse().getCourseHoursList(), course.getCourseHoursList()))
//                return EXISTS_TIME;
//        }
//
//        return SUCCESS;
//    }

    private boolean isDuplicateCourse(Course course, Timetable timetable) {
        return courseTimetableRepository.existsByCourseAndTimetable(course, timetable);
    }

    // 리스트를 가져와서 하나씩 비교하지 말고 DB에서 비교해서 있는지 확인하는게 빠를듯?
    private boolean isDuplicateTime(List<CourseHours> hours1, List<CourseHours> hours2) {
        for(CourseHours h1 : hours1) {
            for(CourseHours h2 : hours2) {
                if(h1.getDay().equals(h2.getDay())) {
                    if(h1.getHours().getId().equals(h2.getHours().getId()))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean isDuplicateTime() {
        return false;
    }
}
