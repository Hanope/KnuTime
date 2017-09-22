package com.knutime.controller;

import com.knutime.domain.course.Course;
import com.knutime.domain.rating.Rating;
import com.knutime.exception.ResourceNotFoundException;
import com.knutime.service.course.CourseService;
import com.knutime.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private RatingService ratingService;

    @RequestMapping("/{courseId}")
    public ModelAndView viewCourse(@PathVariable Long courseId, @PageableDefault(sort = {"dateTime"},
                direction = Sort.Direction.DESC, size = 6)Pageable pageable) {

        Course course = courseService.getCourseInfoById(courseId);
        Page<Rating> ratings = ratingService.findAllByCourseId(pageable, courseId);

        if(ratings.getTotalPages() == 0)
            throw new ResourceNotFoundException();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/course/course");
        modelAndView.addObject("course", course);
        modelAndView.addObject("ratings", ratings);

        return modelAndView;
    }
}
