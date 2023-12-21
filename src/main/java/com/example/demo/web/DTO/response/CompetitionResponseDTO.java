package com.example.demo.web.DTO.response;

import com.example.demo.entity.Competition;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record CompetitionResponseDTO(
        String code,
        String date,
        String endDate,
        String startTime,
        String endTime,
        int numberOfParticipants,
        String location,
        int amount
) {
    public static CompetitionResponseDTO fromCompetition(Competition competition) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM d, yyyy", Locale.US);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.US);
        return new CompetitionResponseDTO(
                competition.getCode(),
                competition.getDate().format(dateFormatter),
                competition.getEndDate().format(dateFormatter),
                competition.getStartTime().format(timeFormatter),
                competition.getEndTime().format(timeFormatter),
                competition.getNumberOfParticipants(),
                competition.getLocation(),
                competition.getAmount()
        );
    }
}
