package com.example.demo.web.DTO.request;

import com.example.demo.entity.Level;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LevelRequestDTO(
        @NotNull(message = "description is required")
        @NotBlank(message = "description is required")
        @Size(min = 3, max = 255, message = "description must be between 3 and 255 characters")
        String description,

        @NotNull(message = "point is required")
        @Min(value = 1, message = "point must be greater than 0")
        Integer point
) {
    public Level toLevel() {
        return Level.builder()
                .description(description)
                .point(point)
                .build();
    }
}
