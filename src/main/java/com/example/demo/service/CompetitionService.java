package com.example.demo.service;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Member;
import com.example.demo.entity.Ranking;

import java.util.List;

public interface CompetitionService {
    Competition getCompetitionByCode(String code);
    List<Competition> getAllCompetitions();
    Competition addCompetition(Competition competition);
    Competition updateCompetition(Competition competition, String code);
    void deleteCompetition(String code);
    Ranking registerMemberForCompetition(Ranking ranking);
    List<Ranking> registerMembersForCompetition(List<Ranking> rankings);
    List<Member> getMembersByCompetitionId(Long id);
    List<Ranking> getRankingsByCompetitionId(Long id);
}
