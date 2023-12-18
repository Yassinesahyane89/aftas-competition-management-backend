package com.example.demo.web.DTO.response;

public record FishResponseDTO(
        Long id,
        String name,
        double averageWeight,
        LevelResponseDTO level
) {
    public static FishResponseDTO fromFish(com.example.demo.entity.Fish fish) {
        return new FishResponseDTO(
                fish.getId(),
                fish.getName(),
                fish.getAverageWeight(),
                LevelResponseDTO.fromLevel(fish.getLevel())
        );
    }
}
