package com.pranay.movie_info_service.repository;

import com.pranay.movie_info_service.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInfoRepository extends JpaRepository<Movie, String> {
}
