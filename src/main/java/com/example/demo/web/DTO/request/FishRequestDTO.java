package com.example.demo.web.DTO.request;

public record FishRequestDTO(
        String name,
        double averageWeight,
        Long levelId
) {

}
