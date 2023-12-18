package com.example.demo.service;

import com.example.demo.entity.Fish;

import java.util.List;

public interface FishService {
    Fish getFishById(Long id);
    List<Fish> getAllFishes();
    Fish addFish(Fish fish);
    Fish updateFish(Fish fish, Long id);
    void deleteFish(Long id);

}
