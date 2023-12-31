package com.example.demo.service.Impl;

import com.example.demo.entity.Level;
import com.example.demo.handler.exception.OperationException;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.LevelRepository;
import com.example.demo.service.LevelService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
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
        return levelRepository.findAll();
    }

    @Override
    public Level addLevel(Level level) {
        List<Level> levels = getAllLevels();

        // if there is no level
        if(levels.isEmpty()){
            level.setCode(1);
            return levelRepository.save(level);
        }

        // check if this point exist
        if(levelRepository.findByPoint(level.getPoint()) != null){
            throw new OperationException("Point already exist");
        }

        // get all levels that are have point greater than the new level point
        List<Level> levels1 = levelRepository.findAllByCodeGreaterThan(level.getPoint());

        // if there is no level with point greater than the new level point
        if(levels1.isEmpty()){
            Level lastLevel = levels.get(levels.size()-1);
            level.setCode(lastLevel.getCode()+1);
            return levelRepository.save(level);
        }

        // if there is level with point greater than the new level point update the code of the levels by incrementing by 1 and save the new level
        for(Level level1: levels1){
            level.setCode(level1.getCode());
            level1.setCode(level1.getCode()+1);
            levelRepository.save(level1);
        }

        // save the new level
        return levelRepository.save(level);
    }

    @Transactional
    @Override
    public Level updateLevel(Level level, Long id) {
        Level existingLevel = getLevelById(id);
        existingLevel.setDescription(level.getDescription());

        // check if point is not changed
        if(existingLevel.getPoint() == level.getPoint()){
            return levelRepository.save(existingLevel);
        }

        // get all levels
        List<Level> levels1 = levelRepository.findAll();

        // if there is only one level
        if(levels1.size()==1){
            existingLevel.setPoint(level.getPoint());
            return levelRepository.save(existingLevel);
        }

        // check if point is greater than previous level and less than next level
        int index = existingLevel.getCode()-1;
        if(index==0){
            if(level.getPoint() >= levels1.get((index+1)).getPoint()) {
                throw new ResolutionException("Point must be less than " + levels1.get((index+1)).getPoint());
            }
        }else if(index==levels1.size()-1) {
            if (level.getPoint() <= levels1.get((index - 1)).getPoint()) {
                throw new ResolutionException("Point must be greater than " + levels1.get((index - 1)).getPoint());
            }
        }else{
            if(level.getPoint() >= levels1.get((index+1)).getPoint()) {
                throw new OperationException("Point must be less than " + levels1.get((index+1)).getPoint());
            }
            if(level.getPoint() <= levels1.get((index-1)).getPoint()) {
                throw new OperationException("Point must be greater than " + levels1.get((index-1)).getPoint());
            }
        }
        existingLevel.setPoint(level.getPoint());
        return levelRepository.save(existingLevel);
    }

    @Override
    public void deleteLevel(Long id) {
        // get the level
        Level level = getLevelById(id);

        // delete the level
        levelRepository.deleteById(id);

        // get all levels that are have code greater than the deleted level code
        List<Level> levels = levelRepository.findAllByCodeGreaterThan(level.getCode());
        for(Level level1: levels){
            level1.setCode(level1.getCode()-1);
            levelRepository.save(level1);
        }
    }
}
