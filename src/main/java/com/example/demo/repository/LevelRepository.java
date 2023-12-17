package com.example.demo.repository;

import com.example.demo.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findAllByPointGreaterThan(Integer point);
}
