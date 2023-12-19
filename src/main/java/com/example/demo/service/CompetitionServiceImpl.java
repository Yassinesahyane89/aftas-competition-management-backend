package com.example.demo.service;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Member;
import com.example.demo.entity.Ranking;

import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    @Override
    public Competition getCompetitionById(Long id) {
        return null;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return null;
    }

    @Override
    public Competition addCompetition(Competition competition) {
        return null;
    }

    @Override
    public Competition updateCompetition(Competition competition, Long id) {
        return null;
    }

    @Override
    public void deleteCompetition(Long id) {

    }

    @Override
    public Ranking registerMemberForCompetition(Ranking ranking) {
        return null;
    }

    @Override
    public List<Ranking> registerMembersForCompetition(List<Ranking> rankings) {
        return null;
    }

    @Override
    public List<Member> getMembersByCompetitionId(Long id) {
        return null;
    }

    @Override
    public List<Ranking> getRankingsByCompetitionId(Long id) {
        return null;
    }
}
