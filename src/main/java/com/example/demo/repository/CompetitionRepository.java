package com.example.demo.repository;

import com.example.demo.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByDate(LocalDate date);

    Competition findByCode(String code);

    List<Competition> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Competition> findAllByDateBefore(LocalDate date);

    List<Competition> findAllByDateAfter(LocalDate date);
}
