package com.jlozano.eron_challenge.repository.impl;

import com.jlozano.eron_challenge.config.ApiConfig;
import com.jlozano.eron_challenge.dto.Movie;
import com.jlozano.eron_challenge.dto.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.jlozano.eron_challenge.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public MovieRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private ApiConfig apiConfig;

    @Override
    public List<Movie> getAllMovies() {

        List<Movie> allMovies = new ArrayList<>();
        int page = 1;
        MovieResponse response;

        do {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject(apiConfig.getApiBaseUrl(), MovieResponse.class);
            if (response != null && response.getData() != null) {
                allMovies.addAll(response.getData());
                page++;
            }
        } while (response != null && page <= response.getTotal_pages());

        return allMovies;
    }
}