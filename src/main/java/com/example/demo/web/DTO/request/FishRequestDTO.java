package com.example.demo.web.DTO.request;

import com.example.demo.entity.Fish;
import com.example.demo.entity.Level;

public record FishRequestDTO(
        String name,
        double averageWeight,
        Long levelId
) {
     public static Fish toFish(FishRequestDTO fishRequestDTO) {
      return  Fish.builder()
              .name(fishRequestDTO.name())
              .averageWeight(fishRequestDTO.averageWeight())
              .level(Level.builder().id(fishRequestDTO.levelId()).build())
            .build();
     }
}
