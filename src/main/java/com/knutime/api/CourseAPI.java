package com.knutime.api;

import com.knutime.domain.ajax.AjaxResponseObject;
import com.knutime.domain.course.Course;
import com.knutime.domain.course.CourseSummary;
import com.knutime.domain.timetable.Timetable;
import com.knutime.service.course.CourseService;
import com.knutime.service.timetable.TimetableService;
import com.knutime.support.CustomErrorType;
import com.knutime.support.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseAPI {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/{param}")
    public ResponseEntity<?> getCourseSummary(@RequestParam("serialNumber") String serialNumber, @PathVariable String param) {
        Timetable timetable = timetableService.getTimetable(serialNumber);
        String semester = timetable.getSemester();
        List<CourseSummary> courses = courseService.getCourseSummary(semester, param);

        if(courses.isEmpty()) {
            return new ResponseEntity(new CustomErrorType(ErrorMessage.ERR_NOT_FOUND_COURSE),
                    HttpStatus.NOT_FOUND);
        } else {
            AjaxResponseObject result = new AjaxResponseObject();

            result.success();
            result.setMessage("course load success");
            result.setResult(courses);

            return ResponseEntity.ok(result);
        }
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

    @GetMapping("/search/{name}")
    public Map<String, Object> getCourseName(@PathVariable String name) {
        Map<String, Object> result = new HashMap<>();

        List<Course> courses = courseService.getCourseList(name);

        if(courses.size() != 0)
            result.put("result", courses);
        else
            result.put("result", "존재하지 않습니다.");

        return result;
    }
}
