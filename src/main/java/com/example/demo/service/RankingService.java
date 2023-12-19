package com.example.demo.service;

import com.example.demo.entity.Ranking;

import java.util.List;

public interface RankingService {
    Ranking getRankingByCompetitionCodeAndMemberNumber(String competitionCode, Long memberNumber);
    List<Ranking> getAllRankings();
    Ranking addRanking(Ranking ranking);
    List<Ranking> addRankings(List<Ranking> rankings);
    Ranking updateScoreOfMemberInCompetition(String competitionCode, Long memberNumber, Integer score);
    List<Ranking> updateRankOfMemberInCompetition(String competitionCode);
    void deleteRanking(String competitionCode, Long memberNumber);

}
