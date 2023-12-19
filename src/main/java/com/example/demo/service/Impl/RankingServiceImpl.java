package com.example.demo.service.Impl;

import com.example.demo.entity.Ranking;
import com.example.demo.handler.exception.ResourceNotFountException;
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
        Ranking ranking = rankingRepository.findAllByMemberMembershipNumberAndCompetitionCode(memberNumber, competitionCode);
        if(ranking == null) {
            throw new ResourceNotFountException("Ranking for competition code " + competitionCode + " and member number " + memberNumber + " not found");
        }
        return ranking;
    }

    @Override
    public List<Ranking> getAllRankings() {
        return rankingRepository.findAll();
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
    public List<Ranking> updateRankOfMemberInCompetition(String competitionCode) {
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
