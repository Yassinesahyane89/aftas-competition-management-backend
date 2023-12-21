package com.example.demo.web.rest.controller;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Ranking;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.CompetitionService;
import com.example.demo.web.DTO.request.CompetitionRequestDTO;
import com.example.demo.web.DTO.request.FilterrequestDTO;
import com.example.demo.web.DTO.request.RegisterMembersRequestDTO;
import com.example.demo.web.DTO.response.CompetitionResponseDTO;
import com.example.demo.web.DTO.response.CompetitionUpdateResponseDTO;
import com.example.demo.web.DTO.response.RankingResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
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

    //get all competitions with pagination
    @GetMapping("/page")
    public ResponseEntity getAllCompetitions(@RequestParam int page, @RequestParam int size) {
        List<Competition> competitions = competitionService.getAllCompetitions(PageRequest.of(page, size));
        if(competitions.isEmpty()) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            List<CompetitionResponseDTO> competitionResponseDTOS = new ArrayList<>();
            for(Competition competition : competitions) {
                competitionResponseDTOS.add(CompetitionResponseDTO.fromCompetition(competition));
            }
            return ResponseMessage.ok(competitions, "Success");
        }
    }

    //get competition by code
    @GetMapping("/{code}")
    public ResponseEntity getCompetitionByCode( @PathVariable String code) {
        Competition competition = competitionService.getCompetitionByCode(code);
        if(competition == null) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok(CompetitionUpdateResponseDTO.fromCompetition(competition), "Success");
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

    // update competition
    @PutMapping("/{code}")
    public ResponseEntity updateCompetition(@PathVariable String code, @Valid @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        Competition updatedCompetition = competitionService.updateCompetition(competitionRequestDTO.toCompetition(),code);
        if(updatedCompetition == null) {
            return ResponseMessage.badRequest("Competition not updated");
        }else {
            return ResponseMessage.ok(CompetitionResponseDTO.fromCompetition(updatedCompetition), "Competition updated successfully");
        }
    }

    // register members to competition
    @PostMapping("/register-member")
    public ResponseEntity registerMembersToCompetition(@Valid @RequestBody RegisterMembersRequestDTO rankings) {
        List<Ranking> rankings1 = competitionService.registerMembersForCompetition(rankings.toRanking());
        if(rankings1 == null) {
            return ResponseMessage.badRequest("Members not registered");
        }else {
            List<RankingResponseDTO> rankingResponseDTOS = new ArrayList<>();
            for(Ranking ranking : rankings1) {
                rankingResponseDTOS.add(RankingResponseDTO.fromRanking(ranking));
            }
            return ResponseMessage.created(rankingResponseDTOS, "Members registered successfully");
        }
    }

    // update rank of competition
    @PutMapping("/{code}/rank")
    public ResponseEntity updateRankOfCompetition(@PathVariable String code) {
        List<Ranking> rankings = competitionService.updateRankOfMemberInCompetition(code);
        if(rankings == null) {
            return ResponseMessage.badRequest("Rank not updated");
        }else {
            List<RankingResponseDTO> rankingResponseDTOS = new ArrayList<>();
            for(Ranking ranking : rankings) {
                rankingResponseDTOS.add(RankingResponseDTO.fromRanking(ranking));
            }
            return ResponseMessage.ok(rankingResponseDTOS, "Rank updated successfully");
        }
    }

    // List competitions with a filter (ongoing, closed, upcoming)
    @GetMapping("/filter")
    public ResponseEntity getCompetitionsByFilter(@RequestParam String filter) {
        List<Competition> competitions = competitionService.getCompetitionsByFilter(filter);
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

    // return boolean if the competition is ended or not and also must not pass 1 day from the end date
    @GetMapping("/{code}/is-ended")
    public ResponseEntity isCompetitionEnded(@PathVariable String code) {
        boolean isEnded = competitionService.isCompetitionEnded(code);
        if(isEnded) {
            return ResponseMessage.ok(isEnded, "Competition is ended");
        }else {
            return ResponseMessage.ok(isEnded, "Competition is not ended");
        }
    }

}
