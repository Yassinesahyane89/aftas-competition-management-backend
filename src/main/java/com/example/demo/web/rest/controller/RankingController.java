package com.example.demo.web.rest.controller;

import com.example.demo.entity.Ranking;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rankings")
@CrossOrigin("*")
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    //updateRankOfMemberInCompetition

}
