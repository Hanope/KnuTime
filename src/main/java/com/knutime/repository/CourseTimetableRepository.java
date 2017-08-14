package com.knutime.repository;

import com.knutime.domain.timetable.CourseTimetable;
import com.knutime.domain.timetable.CourseTimetableId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTimetableRepository extends JpaRepository<CourseTimetable, CourseTimetableId> {
    CourseTimetable findOneByCourse_Id(Long id);
}
