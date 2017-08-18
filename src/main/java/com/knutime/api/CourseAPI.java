package com.knutime.api;

import com.knutime.domain.ajax.AjaxResponseObject;
import com.knutime.domain.course.Course;
import com.knutime.domain.course.CourseSummary;
import com.knutime.service.course.CourseService;
import com.knutime.util.CustomErrorType;
import com.knutime.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseAPI {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{param}")
    public ResponseEntity<?> getCourseSummary(@PathVariable String param) {
        AjaxResponseObject result = new AjaxResponseObject();
        List<CourseSummary> courses = courseService.getCourseSummaryByTitleOrCode(param);

        if(courses.isEmpty()) {
            return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_NOT_FOUND_COURSE),
                    HttpStatus.NOT_FOUND);
        } else {
            result.success();
            result.setMessage("course load success");
            result.setResult(courses);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getCourseInfo(@PathVariable Long id) {
        AjaxResponseObject result = new AjaxResponseObject();
        Course course = courseService.getCourseInfoById(id);

        if(course == null) {
            return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_NOT_FOUND_COURSE),
                    HttpStatus.NOT_FOUND);
        } else {
            result.success();
            result.setMessage("course load success");
            result.setResult(course);
        }

        return ResponseEntity.ok(result);
    }
}
