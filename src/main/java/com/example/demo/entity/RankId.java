package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class RankId implements Serializable {

    @Column(name = "member_membership_number")
    private Long memberNumber;

    @Column(name = "competition_code")
    private String competitionCode;
}
