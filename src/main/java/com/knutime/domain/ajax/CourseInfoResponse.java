package com.knutime.domain.ajax;

import com.knutime.domain.course.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseInfoResponse extends AjaxResponse {

    private Course result;
}
