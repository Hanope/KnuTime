package com.knutime.repository;

import com.knutime.domain.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDateTimeAfter(Date date);
}
