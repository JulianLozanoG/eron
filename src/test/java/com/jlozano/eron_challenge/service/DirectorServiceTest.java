package com.jlozano.eron_challenge.service;

import com.jlozano.eron_challenge.EronChallengeApplication;
import com.jlozano.eron_challenge.dto.Movie;
import com.jlozano.eron_challenge.dto.MovieResponse;
import com.jlozano.eron_challenge.repository.impl.MovieRepositoryImpl;
import com.jlozano.eron_challenge.service.impl.DirectorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EronChallengeApplication.class)
class DirectorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MovieRepositoryImpl movieRepositoryImpl;

    private DirectorService directorService;

    @BeforeEach
    void setUp() {
        directorService = new DirectorServiceImpl(movieRepositoryImpl);
    }

    @Test
    void getDirectorsTest() {
        Movie movie1 = new Movie();
        movie1.setTitle("Movie 1");
        movie1.setDirector("Director A");

        Movie movie2 = new Movie();
        movie2.setTitle("Movie 2");
        movie2.setDirector("Director B");

        Movie movie3 = new Movie();
        movie3.setTitle("Movie 3");
        movie3.setDirector("Director A");

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setPage(1);
        movieResponse.setPer_page(3);
        movieResponse.setTotal(3);
        movieResponse.setTotal_pages(1);
        movieResponse.setData(Arrays.asList(movie1, movie2, movie3));

        when(restTemplate.getForObject(anyString(), any())).thenReturn(movieResponse);
        when(movieRepositoryImpl.getAllMovies()).thenReturn(Arrays.asList(movie1, movie2, movie3));

        List<String> directors = directorService.getDirectors(1);

        assertEquals(1, directors.size());
        assertEquals("Director A", directors.get(0));
    }

}