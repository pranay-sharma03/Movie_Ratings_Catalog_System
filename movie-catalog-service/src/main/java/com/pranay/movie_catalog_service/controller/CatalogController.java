package com.pranay.movie_catalog_service.controller;

import com.pranay.movie_catalog_service.model.Catalog;
import com.pranay.movie_catalog_service.model.Movie;
import com.pranay.movie_catalog_service.model.Rating;
import com.pranay.movie_catalog_service.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // return user catalog details given userId
    @GetMapping("/{userId}")
    public List<Catalog> getCatalogById(@PathVariable String userId){

        // get all movie ids and their ratings for given userId
        UserRating ratingList = restTemplate.getForObject("http://ratings-data-service/ratingsData/users/" + userId, UserRating.class);
        List<Rating> ratings = ratingList.getRatingList();

        // use the movieIds in the ratings to get movie info
        return ratings.stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

                    /*
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    */

                    // construct required catalog element using the obtained fields
                    return new Catalog(movie.getMovieId(), movie.getDesc(), rating.getRating());
                })
                .collect(Collectors.toList());

    }

}
