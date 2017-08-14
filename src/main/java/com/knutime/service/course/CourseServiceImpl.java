package com.knutime.service.course;

import com.knutime.domain.course.Course;
import com.knutime.domain.course.CourseSummary;
import com.knutime.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseSummary> getCourseSummaryByTitleOrCode(String param) {
        LOGGER.debug("Getting course={}", param);
        List<Course> courseList = courseRepository.findByTitleStartingWithOrCodeStartingWith(param, param);
        List<CourseSummary> courseSummaryList = new ArrayList<>();

        for(Course course : courseList) {
            CourseSummary courseSummary = new CourseSummary(course);
            courseSummaryList.add(courseSummary);
        }

        return courseSummaryList;
    }

    @Override
    public Course getCourseInfoById(Long id) {
        return courseRepository.findOne(id);
    }
}