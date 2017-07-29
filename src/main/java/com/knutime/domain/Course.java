package com.knutime.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "test")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "course_title", nullable = false)
    private String title;

    @Column(name = "course_code", nullable = false)
    private String code;

    @Column(name = "credits", nullable = false)
    private String credits;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "semester", nullable = false)
    private String semester;

    @Column(name = "course_categories", nullable = false)
    private String categories;

    @Column(name = "instructor", nullable = false)
    private String instructor;

    @Column(name = "hours", nullable = false)
    private String hours;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "phone_email", nullable = false)
    private String phoneEmail;

    @Column(name = "office_hours", nullable = false)
    private String officeHours;

    @Column(name = "language", nullable = false)
    private String language;

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
                '}';
    }
}
