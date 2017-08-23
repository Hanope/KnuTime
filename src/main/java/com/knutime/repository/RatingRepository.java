package com.knutime.repository;

import com.knutime.domain.rating.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByDateTimeAfterOrderByDateTimeDesc(Date date);
}
