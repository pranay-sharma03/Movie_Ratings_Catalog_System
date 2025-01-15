package com.pranay.ratings_data_service.controller;

import com.pranay.ratings_data_service.model.Rating;
import com.pranay.ratings_data_service.model.UserRating;
import com.pranay.ratings_data_service.service.RatingService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsData")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId){
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getRatings(@PathVariable String userId){
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 5)
//        );

        List<Rating> ratings = ratingService.getRatings(userId);

        UserRating userRating = new UserRating();
        userRating.setRatingList(ratings);
        return userRating;
    }

}
