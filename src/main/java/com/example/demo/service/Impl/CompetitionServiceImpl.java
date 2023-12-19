package com.example.demo.service.Impl;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Member;
import com.example.demo.entity.Ranking;
import com.example.demo.handler.exception.OperationException;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.CompetitionRepository;
import com.example.demo.service.CompetitionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }
    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Competition id " + id + " not found"));
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
