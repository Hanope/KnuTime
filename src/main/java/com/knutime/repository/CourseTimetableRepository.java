package com.knutime.repository;

import com.knutime.domain.course.Course;
import com.knutime.domain.timetable.CourseTimetable;
import com.knutime.domain.timetable.CourseTimetableId;
import com.knutime.domain.timetable.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseTimetableRepository extends JpaRepository<CourseTimetable, CourseTimetableId> {

    CourseTimetable findOneByCourse_Id(Long id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM CourseTimetable c " +
            "WHERE c.timetable = :timetable AND c.course = :course ")
    boolean existsCourse(@Param("timetable") Timetable timetable, @Param("course") Course course);

    @Query("SELECT CASE WHEN COUNT(c1.hours) > 0 THEN true ELSE false END " +
            "FROM CourseHours c1 " +
            "INNER JOIN c1.course.courseTimetableList c2 " +
            "WHERE c2.timetable = :timetable AND c1.hours IN(" +
                "SELECT c3.hours " +
                "FROM CourseHours c3 " +
                "WHERE c3.course = :course)" +
            "")
    boolean existsCourseTime(@Param("timetable") Timetable timetable, @Param("course") Course course);
}
