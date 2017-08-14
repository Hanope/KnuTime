package com.knutime.domain.timetable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.knutime.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "semester", nullable = false)
    private String semester;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @OneToMany(mappedBy = "timetable")
    private List<CourseTimetable> courseTimetableList = new ArrayList<>();

    public Timetable() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                ", semester=" + semester +
                '}';
    }
}
