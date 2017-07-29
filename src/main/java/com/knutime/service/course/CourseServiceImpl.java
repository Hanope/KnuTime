package com.knutime.service.course;

import com.knutime.domain.Course;
import com.knutime.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getCourseByTitleOrCode(String param) {
        LOGGER.debug("Getting course={}", param);
        return courseRepository.findByTitleStartingWithOrCodeStartingWith(param, param);
    }
}