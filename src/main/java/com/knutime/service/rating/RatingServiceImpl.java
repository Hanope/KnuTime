package com.knutime.service.rating;

import com.knutime.domain.course.Course;
import com.knutime.domain.rating.Rating;
import com.knutime.domain.user.CurrentUser;
import com.knutime.dto.RatingDTO;
import com.knutime.repository.CourseRepository;
import com.knutime.repository.RatingRepository;
import com.knutime.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Page<Rating> findAll(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }

    @Override
    public Page<Rating> findAllByCourseAndInstructor(Pageable pageable, String param) {
        Course course = courseRepository.findByTitleStartingWithOrInstructorStartingWith(param, param);

        return ratingRepository.findAllByCourseOrderByDateTimeDesc(pageable, course);
    }

    @Override
    public Page<Rating> findAllByCourseId(Pageable pageable, Long courseId) {
        Course course = courseRepository.findOne(courseId);

        return ratingRepository.findAllByCourse(pageable, course);
    }

    @Override
    public Rating save(CurrentUser user, Long courseId, RatingDTO ratingDTO) {
        Course course = courseRepository.findOne(courseId);

        Rating rating = new Rating();
        rating.setUser(user.getUser());
        rating.setStar(ratingDTO.getStar());
        rating.setComment(ratingDTO.getComment());
        rating.setCourse(course);

        ratingRepository.save(rating);

        return rating;
    }

    @Override
    public Page<Rating> findAllByCourseName(Pageable pageable, String courseName) {
        Course course = courseRepository.findOneByTitle(courseName);

        return ratingRepository.findAllByCourse(pageable, course);
    }
}