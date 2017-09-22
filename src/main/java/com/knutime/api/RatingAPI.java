package com.knutime.api;

import com.knutime.domain.rating.Rating;
import com.knutime.domain.user.CurrentUser;
import com.knutime.dto.RatingDTO;
import com.knutime.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rating")
public class RatingAPI {

    @Autowired
    private RatingService ratingService;

    @GetMapping(value = "{courseId}")
    public Page<Rating> findAllRating(@PageableDefault(sort = {"dateTime"},
            direction = Sort.Direction.DESC, size = 10) Pageable pageable, @PathVariable(name = "courseId") String courseId) {

        return null;
    }

    @PostMapping(value = "{courseId}")
    public Map<String, Object> writeRating(@PathVariable(name = "courseId") Long courseId,
                                           @RequestBody RatingDTO ratingDTO) {

        Map<String, Object> result = new HashMap<>();
        CurrentUser user = CurrentUser.getCurrentUser();

        if(user == null) {
            result.put("status", "fail");
            result.put("message", "로그인이 필요합니다.");
            result.put("url", "/login");

            return result;
        }

        Rating rating = ratingService.save(user, courseId, ratingDTO);
        if(rating != null) {
            result.put("status", "success");
            result.put("message", "강의평가가 등록되었습니다.");
            result.put("data", rating.getComment());
        }

        return result;
    }
}