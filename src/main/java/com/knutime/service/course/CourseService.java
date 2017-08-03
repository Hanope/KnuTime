package com.knutime.service.course;

import com.knutime.domain.Course;
import com.knutime.domain.CourseInfo;
import com.knutime.domain.CourseSummary;

import java.util.List;

public interface CourseService {

    List<CourseSummary> getCourseSummaryByTitleOrCode(String param);

    Course getCourseInfoById(Long id);
}
