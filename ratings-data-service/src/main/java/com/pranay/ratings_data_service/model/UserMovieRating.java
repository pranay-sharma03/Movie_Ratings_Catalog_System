package com.pranay.ratings_data_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UserMovieRating {
    @EmbeddedId
    private UserMovieRatingId id;
    private int rating;

    public UserMovieRating() {
    }

    public UserMovieRating(UserMovieRatingId id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public UserMovieRatingId getId() {
        return id;
    }

    public void setId(UserMovieRatingId id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
