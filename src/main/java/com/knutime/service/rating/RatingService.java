package com.knutime.service.rating;

import com.knutime.domain.rating.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RatingService {

    Page<Rating> findAll(Pageable pageable);

    List<Rating> findRatingAll();
}
