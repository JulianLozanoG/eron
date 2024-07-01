package com.jlozano.eron_challenge.service.impl;

import com.jlozano.eron_challenge.dto.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jlozano.eron_challenge.repository.MovieRepository;
import com.jlozano.eron_challenge.service.DirectorService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectorServiceImpl implements DirectorService {
    private final MovieRepository movieRepository;

    @Autowired
    public DirectorServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<String> getDirectors(int threshold) {
        List<Movie> movies = movieRepository.getAllMovies();

        Map<String, Long> directorCount = movies.stream()
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));

        return directorCount.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }
}
