package com.example.demo.web.DTO.response;

import com.example.demo.entity.Fish;

public record FishResponseDTO(
        Long id,
        String name,
        double averageWeight,
        LevelResponseDTO level
) {
    public static FishResponseDTO fromFish(Fish fish) {
        return new FishResponseDTO(
                fish.getId(),
                fish.getName(),
                fish.getAverageWeight(),
                LevelResponseDTO.fromLevel(fish.getLevel())
        );
    }
}
