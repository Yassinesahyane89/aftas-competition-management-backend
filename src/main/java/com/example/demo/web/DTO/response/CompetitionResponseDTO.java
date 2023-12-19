package com.example.demo.web.DTO.response;

import com.example.demo.entity.Competition;

public record CompetitionResponseDTO(
        String code,
        String date,
        String startTime,
        String endTime,
        int numberOfParticipants,
        String location
) {
    public static CompetitionResponseDTO fromCompetition(Competition competition) {
        return new CompetitionResponseDTO(
                competition.getCode(),
                competition.getDate().toString(),
                competition.getStartTime().toString(),
                competition.getEndTime().toString(),
                competition.getNumberOfParticipants(),
                competition.getLocation()
        );
    }
}
