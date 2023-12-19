package com.example.demo.repository;

import com.example.demo.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findAllByCodeGreaterThan(Integer code);
    Level findByCode(Integer code);
    Level findByPoint(Integer point);

}
