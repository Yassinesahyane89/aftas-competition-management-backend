package com.example.demo.repository;

import com.example.demo.entity.RankId;
import com.example.demo.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankId> {
    Ranking findAllByMemberMembershipNumberAndCompetitionCode(Long memberNumber, String competitionCode);
    List<Ranking> findAllByCompetitionCode(String competitionCode);
}
