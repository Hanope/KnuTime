package com.knutime.controller;

import com.knutime.domain.rating.Rating;
import com.knutime.exception.ResourceNotFoundException;
import com.knutime.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("")
    public ModelAndView ratingView(@PageableDefault(sort = {"dateTime"},
            direction =  Sort.Direction.DESC, size = 15) Pageable pageable) {
        Page<Rating> ratingPage = ratingService.findAll(pageable);

        return new ModelAndView("/rating/rating", "ratingPage", ratingPage);
    }

    @RequestMapping("/course")
    public ModelAndView ratingViewByCourse(@PageableDefault(sort = {"dateTime"},
            direction = Sort.Direction.DESC, size = 10) Pageable pageable, @RequestParam("course") String course) {
        Page<Rating> ratingPage = ratingService.findAllByCourseName(pageable, course);

        return new ModelAndView("/rating/search", "ratingPage", ratingPage);
    }
}
