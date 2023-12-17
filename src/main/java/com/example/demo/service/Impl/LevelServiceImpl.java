package com.example.demo.service.Impl;

import com.example.demo.entity.Level;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.LevelRepository;
import com.example.demo.service.LevelService;

import java.lang.module.ResolutionException;
import java.util.List;

public class LevelServiceImpl implements LevelService {
    private LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }
    @Override
    public Level getLevelById(Long id) {
        return levelRepository.findById(id).orElseThrow(() ->new ResourceNotFountException("Level id " + id + " not found"));
    }

    @Override
    public List<Level> getAllLevels() {
        List<Level> levels =levelRepository.findAll();
        if(levels.isEmpty()){
            throw new ResourceNotFountException("There is no level");
        }
        return levels;
    }

    @Override
    public Level addLevel(Level level) {
        return null;
    }

    @Override
    public Level updateLevel(Level level, Long id) {
        return null;
    }

    @Override
    public void deleteLevel(Long id) {

    }
}
