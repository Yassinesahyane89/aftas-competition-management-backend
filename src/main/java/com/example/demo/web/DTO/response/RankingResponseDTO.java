package com.example.demo.web.DTO.response;

import com.example.demo.entity.Ranking;

public record RankingResponseDTO(
        Integer score,
        Integer rank,
        MemberResponseDTO member,
        CompetitionResponseDTO competition
) {
    public static RankingResponseDTO fromRanking(Ranking ranking) {
        return new RankingResponseDTO(
                ranking.getScore(),
                ranking.getRank(),
                MemberResponseDTO.fromMember(ranking.getMember()),
                CompetitionResponseDTO.fromCompetition(ranking.getCompetition())
        );
    }
}
