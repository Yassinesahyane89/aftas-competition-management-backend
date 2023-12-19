package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Ranking {
    @EmbeddedId
    @Builder.Default
    private RankId id = new RankId();

    private Integer rank;

    private Integer score;

    @ManyToOne
    @MapsId("competitionCode")
    @JoinColumn(name = "competition_code")
    private Competition competition;

}
