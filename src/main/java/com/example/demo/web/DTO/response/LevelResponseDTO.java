package com.example.demo.web.DTO.response;

import com.example.demo.entity.Level;

public record LevelResponseDTO (
        Long id,
        Integer code,
        String description,
        Integer point
){
    public static LevelResponseDTO fromLevel(Level level){
        return new LevelResponseDTO(
                level.getId(),
                level.getCode(),
                level.getDescription(),
                level.getPoint()
        );
    }
}
