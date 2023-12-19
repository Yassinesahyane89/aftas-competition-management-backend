package com.example.demo.service.Impl;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Member;
import com.example.demo.entity.Ranking;
import com.example.demo.handler.exception.OperationException;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.service.CompetitionService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RankingService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final MemberService memberService;

    private final RankingService rankingService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, MemberService memberService, RankingService rankingService) {
        this.competitionRepository = competitionRepository;
        this.memberService = memberService;
        this.rankingService = rankingService;
    }
    @Override
    public Competition getCompetitionByCode(String code) {
        Competition competition = competitionRepository.findByCode(code);
        if(competition == null){
            throw new ResourceNotFountException("Competition code " + code + " not found");
        }
        return competition;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition addCompetition(Competition competition) {
        // check if that there is no competition in the same date
        if(competitionRepository.findByDate(competition.getDate()) != null){
            throw new OperationException("Competition in the same date already exist");
        }

        //check if the competition is in the future date at least 1 day
        if(competition.getDate().isBefore(LocalDate.now().plusDays(1))){
            throw new OperationException("Competition date must be at least 1 day from now");
        }

        // chek if the end time is after the start time
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            throw new OperationException("Competition end time must be after the start time");
        }

        // generate code
        competition.setCode(generateCode(competition.getLocation(), competition.getDate()));

        // save the competition
        return competitionRepository.save(competition);
    }

    public static String generateCode(String location, LocalDate date) {
        String locationCode = location.substring(0, 3).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }

    @Override
    public Competition updateCompetition(Competition competition, String code) {
        // check if the competition exist
        Competition existingCompetition = getCompetitionByCode(code);

        // check if the competition is in the same date
        if(!competition.getDate().equals(existingCompetition.getDate())){
            // check if the competition is in the same date
            if(competitionRepository.findByDate(competition.getDate()) != null){
                throw new OperationException("Competition in the same date already exist");
            }

            //check if the competition is in the future date at least 1 day
            if(competition.getDate().isBefore(LocalDate.now().plusDays(1))){
                throw new OperationException("Competition date must be at least 1 day from now");
            }
        }
        // set date
        existingCompetition.setDate(competition.getDate());

        // chek if the end time is after the start time
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            throw new OperationException("Competition end time must be after the start time");
        }

        // set start time and end time
        existingCompetition.setStartTime(competition.getStartTime());
        existingCompetition.setEndTime(competition.getEndTime());

        // set location
        existingCompetition.setLocation(competition.getLocation());

        // set amount
        existingCompetition.setAmount(competition.getAmount());

        //set numberOfParticipants
        existingCompetition.setNumberOfParticipants(competition.getNumberOfParticipants());

        //  save the competition
        return competitionRepository.save(existingCompetition);
    }

    @Override
    public void deleteCompetition(String code) {
        // check if the competition exist
        Competition existingCompetition = getCompetitionByCode(code);

        // delete the competition
        competitionRepository.delete(existingCompetition);
    }

    @Override
    public Ranking registerMemberForCompetition(Ranking ranking) {
        // check if the competition exist
        Competition competition = getCompetitionByCode(ranking.getCompetition().getCode());

        // check if the member exist
        Member member = memberService.getMemberById(ranking.getMember().getMembershipNumber());

        // check if the member is already registered for the competition
        if(competition.getRanking().stream().anyMatch(ranking1 -> ranking1.getMember().getMembershipNumber().equals(member.getMembershipNumber()))){
            throw new OperationException("Member already registered for the competition");
        }

        // check if the competition is in the future date at least 1 day
        if(competition.getStartTime().isBefore(competition.getStartTime().minusHours(24))){
            throw new OperationException("You can not register for competition that is less than 1 day from now");
        }

        return null;
    }

    @Override
    public List<Ranking> registerMembersForCompetition(List<Ranking> rankings) {
        // check if the competition exist
        Competition competition = getCompetitionByCode(rankings.get(0).getCompetition().getCode());

        // check if the competition is in the future date at least 1 day
        if(competition.getStartTime().isBefore(competition.getStartTime().minusHours(24))){
            throw new OperationException("You can not register for competition that is less than 1 day from now");
        }

        // check if the competition is full
        if(competition.getRanking().size() >= competition.getNumberOfParticipants()){
            throw new OperationException("Competition is full");
        }


        for(Ranking ranking : rankings){
            // check if the member exist
            Member member = memberService.getMemberById(ranking.getMember().getMembershipNumber());

            // check if the member is already registered for the competition
            if(competition.getRanking().stream().anyMatch(ranking1 -> ranking1.getMember().getMembershipNumber().equals(member.getMembershipNumber()))){
                throw new OperationException("Member already registered for the competition");
            }
        }

        // save the rankings
        return rankingService.addRankings(rankings);
    }
}
