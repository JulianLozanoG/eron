package com.jlozano.eron_challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jlozano.eron_challenge.service.DirectorService;

import java.util.List;

@RestController
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/directors")
    public List<String> getDirectors(@RequestParam int threshold) {
        return directorService.getDirectors(threshold);
    }
}
