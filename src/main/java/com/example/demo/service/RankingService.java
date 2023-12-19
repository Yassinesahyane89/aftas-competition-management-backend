package com.example.demo.service;

import com.example.demo.entity.Ranking;

import java.util.List;

public interface RankingService {
    Ranking getRankingByCompetitionCodeAndMemberNumber(String competitionCode, Long memberNumber);
    List<Ranking> getAllRankings();
    Ranking addRanking(Ranking ranking);
    Ranking updateRanking(Ranking ranking, String competitionCode, Long memberNumber);
    List<Ranking> updateRankOfMemberInCompetition(String competitionCode);
    void deleteRanking(String competitionCode, Long memberNumber);
    List<Ranking> getRankingsByCompetitionCode(String competitionCode);
    List<Ranking> getRankingsByMemberNumber(Long memberNumber);

}
