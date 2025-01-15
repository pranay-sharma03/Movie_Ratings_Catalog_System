package com.pranay.movie_info_service.controller;

import com.pranay.movie_info_service.model.Movie;
import com.pranay.movie_info_service.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
    
    @Autowired
    private MovieInfoService service;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId){
        return service.getInfo(movieId);
    }

}
