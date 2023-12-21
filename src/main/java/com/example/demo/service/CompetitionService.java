package com.example.demo.service;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Member;
import com.example.demo.entity.Ranking;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {
    Competition getCompetitionByCode(String code);
    List<Competition> getAllCompetitions();
    List<Competition> getAllCompetitions(Pageable pageable);
    Competition addCompetition(Competition competition);
    Competition updateCompetition(Competition competition, String code);
    void deleteCompetition(String code);
    Ranking registerMemberForCompetition(Ranking ranking);
    List<Ranking> registerMembersForCompetition(List<Ranking> rankings);
    List<Ranking> updateRankOfMemberInCompetition(String competitionCode);

    // List competitions with a filter (ongoing, closed, upcoming)
    List<Competition> getCompetitionsByFilter(String filter);

    // return boolean if the competition is ended or not and also must not pass 1 day from the end date
    boolean isCompetitionEnded(String code);
}
