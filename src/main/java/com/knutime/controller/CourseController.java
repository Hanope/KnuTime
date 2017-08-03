package com.knutime.controller;

import com.knutime.domain.Course;
import com.knutime.domain.CourseInfo;
import com.knutime.domain.CourseSummary;
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
    public List<CourseSummary> getCourse(@PathVariable String param) {
        return courseService.getCourseSummaryByTitleOrCode(param);
    }

    @ResponseBody
    @RequestMapping(value ="/course/info/{id}")
    public Course getCourseInfo(@PathVariable Long id) {
        return courseService.getCourseInfoById(id);
    }
}
