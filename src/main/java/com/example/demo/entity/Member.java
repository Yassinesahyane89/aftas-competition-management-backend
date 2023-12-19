package com.example.demo.entity;

import com.example.demo.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membershipNumber;

    private String firstName;

    private String familyName;

    @Temporal(TemporalType.DATE)
    private LocalDate accessDate;

    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    private List<Ranking> ranking;

    @OneToMany(mappedBy = "member")
    private List<Hunting> hunting;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;
}
