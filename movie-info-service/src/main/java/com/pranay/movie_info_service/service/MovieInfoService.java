package com.pranay.movie_info_service.service;

import com.pranay.movie_info_service.model.Movie;
import com.pranay.movie_info_service.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieInfoService {
    @Autowired
    private MovieInfoRepository repo;

    public Movie getInfo(String movieId) {
        return repo.findById(movieId).get();
    }
}
