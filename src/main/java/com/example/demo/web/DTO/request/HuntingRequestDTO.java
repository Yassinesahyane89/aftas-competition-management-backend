package com.example.demo.web.DTO.request;

import com.example.demo.entity.Competition;
import com.example.demo.entity.Fish;
import com.example.demo.entity.Hunting;
import com.example.demo.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HuntingRequestDTO(
        @NotNull(message = "averageWeight is required")
        @NotBlank(message = "averageWeight cannot be blank")
        @Positive(message = "averageWeight must be positive")
        Double averageWeight,

        @NotNull(message = "FishId is required")
        Long FishId,

        @NotNull(message = "MemberId is required")
        Long membershipNumber,

        @NotNull(message = "CompetitionId is required")
        String code
) {
    public Hunting toHunting() {
        return Hunting.builder()
                .fish(Fish.builder().id(FishId).averageWeight(averageWeight).build())
                .member(Member.builder().membershipNumber(membershipNumber).build())
                .competition(Competition.builder().code(code).build())
                .build();
    }
}
