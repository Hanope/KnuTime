package com.knutime.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knutime.domain.timetable.CourseTimetable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "credits", nullable = false)
    private String credits;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "semester", nullable = false)
    private String semester;

    @Column(name = "categories", nullable = false)
    private String categories;

    @Column(name = "instructor", nullable = false)
    private String instructor;

    // 나중에 컬럼 삭제 해야함.
    @Column(name = "hours", nullable = false)
    private String hours;

    @OneToMany(mappedBy = "course")
    private List<CourseHours> courseHoursList = new ArrayList<>();

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "phone_email", nullable = false)
    private String phoneEmail;

    @Column(name = "office_hours")
    private String officeHours;

    @Column(name = "language", nullable = false)
    private String language;

    @OneToOne
    @JoinColumn(name = "fk_course_info_id")
    private CourseInfo courseInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseTimetable> courseTimetableList = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
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
                ". courseInfo=" + courseInfo +
                '}';
    }
}
