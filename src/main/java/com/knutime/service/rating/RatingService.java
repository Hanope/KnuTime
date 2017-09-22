package com.knutime.service.rating;

import com.knutime.domain.rating.Rating;
import com.knutime.domain.user.CurrentUser;
import com.knutime.dto.RatingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    Page<Rating> findAll(Pageable pageable);

    Page<Rating> findAllByCourseAndInstructor(Pageable pageable, String param);

    Page<Rating> findAllByCourseId(Pageable pageable, Long courseId);

    Rating save(CurrentUser user, Long courseId, RatingDTO ratingDTO);

    Page<Rating> findAllByCourseName(Pageable pageable, String courseName);
}