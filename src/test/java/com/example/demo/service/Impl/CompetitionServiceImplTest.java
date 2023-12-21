package com.example.demo.service.Impl;

import com.example.demo.entity.Competition;
import com.example.demo.handler.exception.OperationException;
import com.example.demo.repository.CompetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CompetitionServiceImplTest {
    @Mock
    private CompetitionRepository competitionRepository;
    @InjectMocks
    private CompetitionServiceImpl competitionServiceImpl;

    private Competition competitionIn3Days;

    @BeforeEach
    void setUp() {
        competitionIn3Days = Competition.builder()
                .code("code")
                .date(LocalDate.now().plusDays(3))
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(5))
                .build();
    }

    @Test
    void addCompetition() {
    }
    @DisplayName("Add competition in occupied date should throw error")
    @Test
    public void addCompetition_inOccupiedDate_shouldThrowError() {
        LocalDate date = LocalDate.now().plusDays(2);
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 0);
        Competition competitionToSave = Competition.builder()
                .date(competitionIn3Days.getDate())
                .build();
        when(competitionRepository.findByDate(competitionToSave.getDate())).thenReturn(competitionIn3Days);
        OperationException exception = assertThrows(OperationException.class, () -> competitionServiceImpl.addCompetition(competitionToSave));
        assertEquals("Competition in the same date already exist", exception.getMessage());
    }

    @DisplayName("Add competition that must be at least 1 day from now should throw error")
    @Test
    public void addCompetition_inDateBefore1DayFromNow_shouldThrowError() {
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 0);
        Competition competitionToSave = Competition.builder()
                .date(date)
                .build();
        OperationException exception = assertThrows(OperationException.class, () -> competitionServiceImpl.addCompetition(competitionToSave));
        assertEquals("Competition date must be at least 1 day from now", exception.getMessage());
    }
}