package com.example.demo.web.rest.controller;

import com.example.demo.entity.Competition;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.CompetitionService;
import com.example.demo.web.DTO.request.CompetitionRequestDTO;
import com.example.demo.web.DTO.response.CompetitionResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
@CrossOrigin("*")
public class CompetitionContoller {
    private final CompetitionService competitionService;

    public CompetitionContoller(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    //get all competitions
    @GetMapping
    public ResponseEntity getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        if(competitions.isEmpty()) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            List<CompetitionResponseDTO> competitionResponseDTOS = new ArrayList<>();
            for(Competition competition : competitions) {
                competitionResponseDTOS.add(CompetitionResponseDTO.fromCompetition(competition));
            }
            return ResponseMessage.ok(competitionResponseDTOS, "Success");
        }
    }

    //get competition by code
    @GetMapping("/{code}")
    public ResponseEntity getCompetitionByCode(String code) {
        Competition competition = competitionService.getCompetitionByCode(code);
        if(competition == null) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok(CompetitionResponseDTO.fromCompetition(competition), "Success");
        }
    }

    //add competition
    @PostMapping
    public ResponseEntity addCompetition(@Valid @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        Competition newCompetition = competitionService.addCompetition(competitionRequestDTO.toCompetition());
        if(newCompetition == null) {
            return ResponseMessage.badRequest("Competition not created");
        }else {
            return ResponseMessage.created(CompetitionResponseDTO.fromCompetition(newCompetition), "Competition created successfully");
        }
    }
}
