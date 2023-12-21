package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Competition {
    @Id
    private String code;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private int numberOfParticipants;

    private String location;

    private int amount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "competition")
    private List<Ranking> ranking;

    @OneToMany(mappedBy = "competition")
    private List<Hunting> hunting;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = true)
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = true)
//    private Date updatedAt;
}
