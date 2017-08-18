package com.knutime.repository;

import com.knutime.domain.course.Course;
import com.knutime.domain.timetable.CourseTimetable;
import com.knutime.domain.timetable.CourseTimetableId;
import com.knutime.domain.timetable.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseTimetableRepository extends JpaRepository<CourseTimetable, CourseTimetableId> {

    CourseTimetable findOneByCourse_Id(Long id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' " +
            "END FROM CourseTimetable c WHERE c.course = ?1 AND c.timetable = ?2")
    boolean existsByCourseAndTimetable(Course course, Timetable timetable);


    boolean existsCourseTimeByCourseAndtimetable(Timetable timetable, Course course);

}
