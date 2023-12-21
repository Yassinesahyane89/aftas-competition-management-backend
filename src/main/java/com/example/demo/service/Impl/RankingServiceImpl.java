package com.example.demo.service.Impl;

import com.example.demo.entity.RankId;
import com.example.demo.entity.Ranking;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.RankingRepository;
import com.example.demo.service.CompetitionService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return rankingRepository.save(ranking);
    }

    @Override
    public List<Ranking> addRankings(List<Ranking> rankings) {
        return rankingRepository.saveAll(rankings);
    }

    @Override
    public Ranking updateScoreOfMemberInCompetition(String competitionCode, Long memberNumber, Integer score) {
        //check that tha competition exist
        // check if the ranking exist
        Ranking ranking = getRankingByCompetitionCodeAndMemberNumber(competitionCode, memberNumber);

        // update the score
        ranking.setScore(score);

        // save the ranking
        return rankingRepository.save(ranking);
    }

    @Override
    public void deleteRanking(String competitionCode, Long memberNumber) {
        // check if the ranking exist
        getRankingByCompetitionCodeAndMemberNumber(competitionCode, memberNumber);

        // delete the ranking
        rankingRepository.deleteById(new RankId(memberNumber, competitionCode));
    }

    // find all ranking by competition code
    @Override
    public List<Ranking> findAllByCompetitionCode(String competitionCode) {
        return rankingRepository.findAllByCompetitionCode(competitionCode);
    }

    // save all ranking
    @Override
    public List<Ranking> saveAll(List<Ranking> rankings) {
        return rankingRepository.saveAll(rankings);
    }
}
