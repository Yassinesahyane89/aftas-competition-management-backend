package com.example.demo.web.DTO.request;

import com.example.demo.entity.Competition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        @NotNull(message = "Date cannot be null")
        LocalDate date,

        @NotBlank(message = "Start time cannot be blank")
        @NotNull(message = "Start time cannot be null")
        LocalTime startTime,

        @NotBlank(message = "End time cannot be blank")
        @NotNull(message = "End time cannot be null")
        LocalTime endTime,

        @NotNull(message = "Number of participants cannot be null")
        @Positive(message = "Number of participants must be positive")
        Integer numberOfParticipants,

        @NotNull(message = "Location cannot be null")
        @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
        String location,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        Integer amount
) {
        public Competition toCompetition() {
                return Competition.builder()
                        .date(date())
                        .startTime(startTime())
                        .endTime(endTime())
                        .numberOfParticipants(numberOfParticipants())
                        .location(location())
                        .amount(amount())
                        .build();
        }
}
