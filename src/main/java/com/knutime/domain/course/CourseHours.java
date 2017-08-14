package com.knutime.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "course_hours")
public class CourseHours {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "day", nullable = false)
    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "fk_hours_id")
    private Hours hours;
}
