package com.knutime.api;

import com.knutime.domain.rating.Rating;
import com.knutime.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/rating")
public class RatingAPI {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("")
    public List<Rating> getRatingAll() {
        return ratingService.findRatingAll();
    }
}
