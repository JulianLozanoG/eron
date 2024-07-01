package com.jlozano.eron_challenge.repository;

import com.jlozano.eron_challenge.dto.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {
    List<Movie> getAllMovies();
}
