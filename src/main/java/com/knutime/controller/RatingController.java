package com.knutime.controller;

import com.knutime.domain.rating.Rating;
import com.knutime.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @RequestMapping("")
    public ModelAndView ratingView(@PageableDefault(sort = {"dateTime"},
            direction =  Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<Rating> ratingPage = ratingService.findAll(pageable);

        return new ModelAndView("rating", "ratingPage", ratingPage);
    }
}
