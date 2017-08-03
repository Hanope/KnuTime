package com.knutime.domain;

import com.knutime.util.Encode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "semester", nullable = false)
    private String semester;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

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
