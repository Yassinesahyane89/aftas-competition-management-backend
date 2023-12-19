package com.example.demo.service.Impl;

import com.example.demo.entity.Hunting;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.HuntingRepository;
import com.example.demo.service.HuntingService;

import java.util.List;

public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;

    public HuntingServiceImpl(HuntingRepository huntingRepository) {
        this.huntingRepository = huntingRepository;
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
