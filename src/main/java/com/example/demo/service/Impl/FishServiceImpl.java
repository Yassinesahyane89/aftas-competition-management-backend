package com.example.demo.service.Impl;

import com.example.demo.entity.Fish;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.FishRepository;
import com.example.demo.service.FishService;
import com.example.demo.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        // check if fish name is not already exist
        if(fishRepository.findByName(fish.getName()) != null) {
            throw new ResourceNotFountException("Fish name " + fish.getName() + " already exist");
        }

        // check if level is already exist
        if(fish.getLevel().getId() != null && levelService.getLevelById(fish.getLevel().getId()) == null){
            throw new ResourceNotFountException("Level id " + fish.getLevel().getId() + " not found");
        }

        return fishRepository.save(fish);
    }

    @Override
    public Fish updateFish(Fish fish, Long id) {
        Fish existingFish = getFishById(id);
        // check if fish name is not already exist and the name is not the same as the existing fish name
        if(fishRepository.findByName(fish.getName()) != null && !existingFish.getName().equals(fish.getName())){
            throw new ResourceNotFountException("Fish name " + fish.getName() + " already exist");
        }
        existingFish.setName(fish.getName());

        // check if level is already exist
        if(levelService.getLevelById(fish.getLevel().getId()) == null) {
            throw new ResourceNotFountException("Level id " + fish.getLevel().getId() + " not found");
        }
        existingFish.setLevel(fish.getLevel());

        return fishRepository.save(existingFish);
    }

    @Override
    public void deleteFish(Long id) {
        getFishById(id);
        fishRepository.deleteById(id);
    }
}
