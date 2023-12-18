package com.example.demo.web.DTO.request;

import com.example.demo.entity.Fish;
import com.example.demo.entity.Level;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record FishRequestDTO(
        @NotNull(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Name must be alphanumeric")
        String name,

        @NotNull(message = "Weight is required")
        @Range(min = 0, max = 1000, message = "Weight must be between 0 and 1000")
        @Positive(message = "Weight must be greater than 0")
        double averageWeight,
        Long levelId
) {
     public Fish toFish() {
      Fish.FishBuilder fish =  Fish.builder()
              .name(name())
              .averageWeight(averageWeight());
                if(levelId() != null) {
                    fish.level(Level.builder().id(levelId()).build());
                }else{
                    fish.level(Level.builder().id(null).build());
                }
                return fish.build();
     }
}
