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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimetableServiceImpl implements TimetableService {

    private static final int EXISTS_COURSE = 100;
    private static final int EXISTS_TIME = 101;
    private static final int SUCCESS = 200;

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

        Timetable timetable = timetableRepository.findOneBySerialNumber(serialNumber);

        return timetable;
    }

    @Override
    public boolean isExistsTimetable(String serialNumber) {
        return timetableRepository.existsBySerialNumber(serialNumber);
    }

    @Override
    public Map<String, Object> addCourse(String timetableId, Long courseId) {
        Map<String, Object> rMap = new HashMap<>();

        Timetable timetable = timetableRepository.findOneBySerialNumber(timetableId);
        Course course = courseRepository.findOne(courseId);

        if(timetable == null || course == null) {
            rMap.put("result", "fail");
            rMap.put("message", "wrong access\nplease check your timetableId and courseId");
            return rMap;
        }

        switch (isDuplicateCourse(timetable, course)) {
            case EXISTS_COURSE:
                rMap.put("result", "fail");
                rMap.put("message", "이미 존재하는 과목입니다.");
                return rMap;
            case EXISTS_TIME:
                rMap.put("result", "fail");
                rMap.put("message", "시간이 중복됩니다.");
                return rMap;
        }

        CourseTimetable courseTimetable = new CourseTimetable(course, timetable);
        courseTimetableRepository.save(courseTimetable);

        rMap.put("result", "success");
        rMap.put("message", "과목이 추가되었습니다.");

        return rMap;
    }

    @Override
    public Map<String, Object> deleteCourse(String timetableId, Long courseId) {
        Map<String, Object> rMap = new HashMap<>();

        Timetable timetable = timetableRepository.findOneBySerialNumber(timetableId);
        CourseTimetable courseTimetable = courseTimetableRepository.findOneByCourse_Id(courseId);

        if(courseTimetable == null) {
            rMap.put("result", "fail");
            rMap.put("message", "wrong access\nplease check your timetableId and courseId");
            return rMap;
        }

        CourseTimetableId courseTimetableId = new CourseTimetableId(courseId, timetable.getId());
        courseTimetableRepository.delete(courseTimetableId);

        rMap.put("result", "success");
        rMap.put("message", "과목이 삭제되었습니다.");

        return rMap;
    }

    private int isDuplicateCourse(Timetable timetable, Course course) {
        Long courseId = course.getId();

        for(CourseTimetable courseTimetable : timetable.getCourseTimetableList()) {
            if(courseTimetable.getCourse().getId() == courseId)
                return EXISTS_COURSE;

            if(isDuplicateTime(courseTimetable.getCourse().getCourseHoursList(), course.getCourseHoursList()))
                return EXISTS_TIME;
        }

        return SUCCESS;
    }

    private boolean isDuplicateTime(List<CourseHours> hours1, List<CourseHours> hours2) {
        for(CourseHours h1 : hours1) {
            for(CourseHours h2 : hours2) {
                if(h1.getDay().equals(h2.getDay())) {
                    if(h1.getHours().getId() == h2.getHours().getId())
                        return true;
                }
            }
        }
        return false;
    }
}
