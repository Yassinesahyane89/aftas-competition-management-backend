package com.example.demo.service.Impl;

import com.example.demo.entity.RankId;
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
        List<Ranking> rankings = rankingRepository.findAllByCompetitionCode(competitionCode);

        // check if there is any ranking for the competition
        if(rankings == null){
            throw new ResourceNotFountException("Rankings for competition code " + competitionCode + " not found");
        }
        // sort the rankings by score and update the rank
        rankings.sort((r1, r2) -> r2.getScore().compareTo(r1.getScore()));

        // update the rank
        for(int i = 0; i < rankings.size(); i++){
            rankings.get(i).setRank(i + 1);
        }

        // save the rankings
        return rankingRepository.saveAll(rankings);
    }

    @Override
    public void deleteRanking(String competitionCode, Long memberNumber) {
        // check if the ranking exist
        getRankingByCompetitionCodeAndMemberNumber(competitionCode, memberNumber);

        // delete the ranking
        rankingRepository.deleteById(new RankId(memberNumber, competitionCode));
    }

    @Override
    public List<Ranking> getRankingsByCompetitionCode(String competitionCode) {
        List<Ranking> rankings = rankingRepository.findAllByCompetitionCode(competitionCode);
        if (rankings == null) {
            throw new ResourceNotFountException("Rankings for competition code " + competitionCode + " not found");
        }
        return rankings;
    }

    @Override
    public List<Ranking> getRankingsByMemberNumber(Long memberNumber) {
        return null;
    }
}
