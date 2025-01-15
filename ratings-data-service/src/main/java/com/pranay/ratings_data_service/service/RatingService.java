package com.pranay.ratings_data_service.service;

import com.pranay.ratings_data_service.model.Rating;
import com.pranay.ratings_data_service.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getRatings(String userId) {
        return ratingRepository.getRatings(userId);
    }
}
