package com.example.demo.service.Impl;

import com.example.demo.entity.*;
import com.example.demo.handler.exception.OperationException;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.HuntingRepository;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        // check if competition exist
        Competition competition = competitionService.getCompetitionByCode(hunting.getCompetition().getCode());
        String code = competition.getCode();

        // check if competition is open by checking the date of competition
        //if(competition.getDate().isBefore(LocalDate.now().plusDays(1))){
        //    throw new OperationException("Competition is closed");
        //}
        // if competition is not end yet we can't add hunting result
        if(competition.getDate().isAfter(LocalDate.now())){
            throw new OperationException("Competition is not end yet");
        }



        // check if member exist
        Member member = memberService.getMemberById(hunting.getMember().getMembershipNumber());
        Long membershipNumber = member.getMembershipNumber();

        // check if fish exist
        Fish fish = fishService.getFishById(hunting.getFish().getId());
        Long fishId = fish.getId();

        // check if Member has already participated in this competition
        Ranking ranking = rankingService.getRankingByCompetitionCodeAndMemberNumber(code, membershipNumber);

        // check weight of fish must be greater than average weight
        if(hunting.getFish().getAverageWeight() < fish.getAverageWeight()){
            throw new ResourceNotFountException("Weight of fish must be greater than average weight");
        }

        // save score of member in this competition
        int score = ranking.getScore() + fish.getLevel().getPoint();
        rankingService.updateScoreOfMemberInCompetition(code, membershipNumber, score);

        // check if fish has already been caught by this member in this competition if yes acquirement the number of fish caught
        Hunting existingHunting = huntingRepository.findByCompetitionCodeAndMemberMembershipNumberAndFishId(code, membershipNumber, fishId);
        if(existingHunting != null){
            existingHunting.setNumberOfHunters(existingHunting.getNumberOfHunters() + 1);
            return huntingRepository.save(existingHunting);
        } else {
            hunting.setNumberOfHunters(1);
            return huntingRepository.save(hunting);
        }
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
