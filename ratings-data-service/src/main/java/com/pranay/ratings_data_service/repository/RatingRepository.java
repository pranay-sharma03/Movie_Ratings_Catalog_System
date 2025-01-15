package com.pranay.ratings_data_service.repository;

import com.pranay.ratings_data_service.model.Rating;
import com.pranay.ratings_data_service.model.UserMovieRating;
import com.pranay.ratings_data_service.model.UserMovieRatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<UserMovieRating, UserMovieRatingId> {

    @Query("SELECT new com.pranay.ratings_data_service.model.Rating(p.id.movieId, p.rating) FROM UserMovieRating p WHERE p.id.userId = :userId")
    List<Rating> getRatings(@Param("userId") String userId);

}

