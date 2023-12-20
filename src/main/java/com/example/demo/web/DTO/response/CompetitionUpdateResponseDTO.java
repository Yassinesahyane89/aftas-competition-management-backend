package com.example.demo.web.DTO.response;

import com.example.demo.entity.Competition;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public record CompetitionUpdateResponseDTO(
        String code,
        String date,
        String startTime,
        String endTime,
        String location,
        int amount,
        int totalMember,
        int numberOfParticipants
) {
    public static CompetitionUpdateResponseDTO fromCompetition(Competition competition) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.US);
        return new CompetitionUpdateResponseDTO(
                competition.getCode(),
                competition.getDate().toString(),
                competition.getStartTime().format(timeFormatter),
                competition.getEndTime().format(timeFormatter),
                competition.getLocation(),
                competition.getAmount(),
                competition.getNumberOfParticipants(),
                competition.getRanking().size()
        );
    }
}
