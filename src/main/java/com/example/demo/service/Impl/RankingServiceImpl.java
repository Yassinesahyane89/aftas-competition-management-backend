package com.example.demo.service.Impl;

import com.example.demo.entity.Ranking;
import com.example.demo.repository.RankingRepository;
import com.example.demo.service.RankingService;

import java.util.List;

public class RankingServiceImpl implements RankingService {
    private final RankingRepository rankingRepository;

    public RankingServiceImpl(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }
    @Override
    public Ranking getRankingByCompetitionCodeAndMemberNumber(String competitionCode, Long memberNumber) {
        return null;
    }

    @Override
    public List<Ranking> getAllRankings() {
        return null;
    }

    @Override
    public Ranking addRanking(Ranking ranking) {
        return null;
    }

    @Override
    public Ranking updateRanking(Ranking ranking, String competitionCode, Long memberNumber) {
        return null;
    }

    @Override
    public void deleteRanking(String competitionCode, Long memberNumber) {

    }

    @Override
    public List<Ranking> getRankingsByCompetitionCode(String competitionCode) {
        return null;
    }

    @Override
    public List<Ranking> getRankingsByMemberNumber(Long memberNumber) {
        return null;
    }
}
