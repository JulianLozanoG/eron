package com.jlozano.eron_challenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Movie> data;
}
