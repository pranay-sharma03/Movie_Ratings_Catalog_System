package com.pranay.movie_catalog_service.model;

public class Movie {
    private String movieId;
    private String desc;


    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.desc = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
