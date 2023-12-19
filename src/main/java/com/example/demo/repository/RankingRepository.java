package com.example.demo.repository;

import com.example.demo.entity.RankId;
import com.example.demo.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, RankId> {

}
