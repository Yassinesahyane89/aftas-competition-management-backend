package com.example.demo.service.Impl;

import com.example.demo.entity.Fish;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.FishRepository;
import com.example.demo.service.FishService;

import java.util.List;

public class FishServiceImpl implements FishService {
    private FishRepository fishRepository;

    public FishServiceImpl(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }
    @Override
    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Fish id " + id + " not found"));
    }

    @Override
    public List<Fish> getAllFishes() {
        return null;
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
