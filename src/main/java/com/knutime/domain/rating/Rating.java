package com.knutime.domain.rating;

import com.knutime.domain.course.Course;
import com.knutime.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "star", nullable = false)
    @Min(0)
    @Max(5)
    private int star;

    @Column(name = "date_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateTime;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_course_id")
    private Course course;
}
