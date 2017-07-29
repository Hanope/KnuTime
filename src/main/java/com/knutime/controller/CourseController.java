package com.knutime.controller;

import com.knutime.domain.Course;
import com.knutime.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ResponseBody
    @RequestMapping(value = "/course/{param}", method = RequestMethod.GET)
    public List<Course> getCourse(@PathVariable String param) {
        return courseService.getCourseByTitleOrCode(param);
    }
}
