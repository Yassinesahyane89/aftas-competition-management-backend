package com.example.demo.repository;

import com.example.demo.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByDate(String date);
}
