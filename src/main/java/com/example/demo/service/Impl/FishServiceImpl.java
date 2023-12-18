package com.example.demo.service.Impl;

import com.example.demo.entity.Fish;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.FishRepository;
import com.example.demo.service.FishService;
import com.example.demo.service.LevelService;

import java.util.List;

public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final LevelService levelService;

    public FishServiceImpl(FishRepository fishRepository, LevelService levelService) {
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }
    @Override
    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Fish id " + id + " not found"));
    }

    @Override
    public List<Fish> getAllFishes() {
        return fishRepository.findAll();
    }

    @Override
    public Fish addFish(Fish fish) {
        return null;
    }

    @Override
    public Fish updateFish(Fish fish, Long id) {
        return null;
    }

    @Override
    public void deleteFish(Long id) {

    }
}
