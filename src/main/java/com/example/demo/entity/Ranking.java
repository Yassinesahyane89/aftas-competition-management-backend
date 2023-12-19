package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

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

    @ManyToOne
    @MapsId("memberNumber")
    @JoinColumn(name = "member_membership_number")
    private Member member;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;
}
