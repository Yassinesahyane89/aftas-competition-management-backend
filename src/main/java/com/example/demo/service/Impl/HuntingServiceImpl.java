package com.example.demo.service.Impl;

import com.example.demo.entity.Hunting;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.HuntingRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    private final RankingService rankingService;
    private final FishService fishService;

    public HuntingServiceImpl(HuntingRepository huntingRepository, MemberService memberService, CompetitionService competitionService, RankingService rankingService, FishService fishService) {
        this.huntingRepository = huntingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
        this.rankingService = rankingService;
        this.fishService = fishService;
    }
    @Override
    public Hunting getHuntingById(Long id) {
        return huntingRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Hunting id " + id + " not found"));
    }

    @Override
    public Hunting addHuntingResult(Hunting hunting) {
        return null;
    }

    @Override
    public List<Hunting> getHuntingsByCompetition(Long competitionId) {
        return null;
    }

    @Override
    public List<Hunting> getHuntingsByCompetitionAndMember(Long competitionId, Long memberId) {
        return null;
    }

    @Override
    public Hunting updateHunting(Hunting hunting, Long id) {
        return null;
    }

    @Override
    public void deleteHunting(Long id) {

    }
}
