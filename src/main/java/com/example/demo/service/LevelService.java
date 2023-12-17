package com.example.demo.service;

import com.example.demo.entity.Level;

import java.util.List;

public interface LevelService {
    Level getLevelById(Long id);
    List<Level> getAllLevels();
    Level addLevel(Level level);
    Level updateLevel(Level level, Long id);
    void deleteLevel(Long id);
}
