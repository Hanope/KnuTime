package com.knutime.service.course;

import com.knutime.domain.Course;
import java.util.List;

public interface CourseService {

    List<Course> getCourseByTitleOrCode(String param);
}
