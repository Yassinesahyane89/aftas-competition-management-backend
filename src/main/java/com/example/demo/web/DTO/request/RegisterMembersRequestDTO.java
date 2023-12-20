package com.example.demo.web.DTO.request;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Member;
import com.example.demo.entity.Ranking;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public record RegisterMembersRequestDTO(
        @NotNull(message = "Member IDs cannot be null")
        String competitionCode,

        @NotNull(message = "Member IDs cannot be null")
        @Size(min = 1, message = "Member IDs cannot be empty")
        Long[] memberIds
) {
    public List<Ranking> toRanking() {
        List<Ranking> rankings = new ArrayList<>();
        if(memberIds != null && memberIds.length > 0) {
            for(Long id : memberIds) {
                rankings.add(Ranking.builder()
                        .competition(Competition.builder().code(competitionCode).build())
                        .member(Member.builder().membershipNumber(id).build())
                        .build());
            }
        }
        return rankings;
    }
}
