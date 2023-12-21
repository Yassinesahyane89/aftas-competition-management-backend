package com.example.demo.service;

import com.example.demo.entity.Ranking;

import java.util.List;

public interface RankingService {
    Ranking getRankingByCompetitionCodeAndMemberNumber(String competitionCode, Long memberNumber);
    List<Ranking> getAllRankings();
    Ranking addRanking(Ranking ranking);
    List<Ranking> addRankings(List<Ranking> rankings);
    Ranking updateScoreOfMemberInCompetition(String competitionCode, Long memberNumber, Integer score);
    void deleteRanking(String competitionCode, Long memberNumber);

    // find all ranking by competition code
    List<Ranking> findAllByCompetitionCode(String competitionCode);

    // save all rankings
    List<Ranking> saveAll(List<Ranking> rankings);
}
