package com.example.demo.web.rest.controller;

import com.example.demo.service.CompetitionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/competitions")
@CrossOrigin("*")
public class CompetitionContoller {
    private final CompetitionService competitionService;

    public CompetitionContoller(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
}
