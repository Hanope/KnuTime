package com.knutime.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseSummary {

    private Long id;

    private String title;

    private String code;

    private String credits;

    private String department;

    private String semester;

    private String categories;

    private String instructor;

    private String hours;

    private String location;

    private String phoneEmail;

    private String officeHours;

    private String language;

    public CourseSummary() {
    }

    public CourseSummary(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.code = course.getCode();
        this.credits = course.getCredits();
        this.department = course.getDepartment();
        this.semester = course.getSemester();
        this.categories = course.getCategories();
        this.instructor = course.getInstructor();
        this.hours = course.getHours();
        this.location = course.getLocation();
        this.phoneEmail = course.getPhoneEmail();
        this.officeHours = course.getOfficeHours();
        this.language = course.getLanguage();
    }

    @Override
    public String toString() {
        return "CourseSummary{" +
                "title=" + title +
                ", code=" + code +
                ", credits='" + credits +
                ", department=" + department +
                ", semester=" + semester +
                ", categories=" + categories +
                ", instructor=" + instructor +
                ", hours=" + hours +
                ", location=" + location +
                ", phoneEmail=" + phoneEmail +
                ", officeHours=" + officeHours +
                ", language=" + language +
                '}';
    }
}
