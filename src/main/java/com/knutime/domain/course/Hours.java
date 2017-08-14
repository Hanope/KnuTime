package com.knutime.domain.course;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Time;

@Entity
@Getter
@Setter
@Table(name = "hours")
public class Hours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;
}
