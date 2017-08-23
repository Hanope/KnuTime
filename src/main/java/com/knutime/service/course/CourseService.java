package com.knutime.service.course;

import com.knutime.domain.course.Course;
import com.knutime.domain.course.CourseSummary;
import java.util.List;

public interface CourseService {

    List<CourseSummary> getCourseSummary(String param, String semester);

    Course getCourseInfoById(Long id);
}
