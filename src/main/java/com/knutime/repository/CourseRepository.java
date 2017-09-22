package com.knutime.repository;

import com.knutime.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitleStartingWithAndSemesterOrCodeStartingWithAndSemester(String title, String semester, String code, String semester2);

    Course findOne(Long id);

    Course findByTitleStartingWithOrInstructorStartingWith(String title, String instructor);

    List<Course> findByTitleStartingWith(String title);

    Course findOneByTitle(String title);
}
