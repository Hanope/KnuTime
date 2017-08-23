package com.knutime.service.rating;

import com.knutime.domain.rating.Rating;
import com.knutime.repository.RatingRepository;
import com.knutime.service.course.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public Page<Rating> findAll(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }

    @Override
    public List<Rating> findRatingAll() {
        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        calendar.setTime(new Date());
        calendar.add(calendar.DAY_OF_MONTH, -5);

        return ratingRepository.findByDateTimeAfterOrderByDateTimeDesc(calendar.getTime());
    }
}
