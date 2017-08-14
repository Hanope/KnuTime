package com.knutime.api;

import com.knutime.domain.ajax.CourseInfoResponse;
import com.knutime.domain.ajax.CourseSummaryResponse;
import com.knutime.domain.course.Course;
import com.knutime.domain.course.CourseSummary;
import com.knutime.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/course/{param}")
    public ResponseEntity<?> getCourseResult(@PathVariable String param) {
        CourseSummaryResponse result = new CourseSummaryResponse();

        List<CourseSummary> courses = courseService.getCourseSummaryByTitleOrCode(param);

        if(courses.isEmpty()) {
            result.fail();
        } else {
            result.success();
        }

        result.setResult(courses);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/course/info/{id}")
    public ResponseEntity<?> getCourseInfo(@PathVariable Long id) {
        CourseInfoResponse result = new CourseInfoResponse();

        Course course = courseService.getCourseInfoById(id);

        if(course == null) {
            result.fail();
        } else {
            result.success();
        }

        result.setResult(course);

        return ResponseEntity.ok(result);
    }
}
