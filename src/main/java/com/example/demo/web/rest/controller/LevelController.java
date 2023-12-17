package com.example.demo.web.rest.controller;

import com.example.demo.entity.Level;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.LevelService;
import com.example.demo.web.DTO.request.LevelRequestDTO;
import com.example.demo.web.DTO.response.LevelResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/levels")
@CrossOrigin("*")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    // get all levels
    @GetMapping
    public ResponseEntity getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        if(levels.isEmpty()) {
            return ResponseMessage.notFound("Levels not found");
        }else {
            List<LevelResponseDTO> levelResponseDTOS = new ArrayList<>();
            for(Level level: levels){
                levelResponseDTOS.add(LevelResponseDTO.fromLevel(level));
            }
            return ResponseMessage.ok(levels, "Success");
        }
    }

    // get level by id
    @GetMapping("/{id}")
    public ResponseEntity getLevelById(@PathVariable Long id) {
        Level level = levelService.getLevelById(id);
        return ResponseMessage.ok(LevelResponseDTO.fromLevel(level), "Success");
    }

    // add level
    @PostMapping
    public ResponseEntity addLevel(@Valid @RequestBody LevelRequestDTO levelRequestDTO) {
        Level level1 = levelService.addLevel(levelRequestDTO.toLevel());
        if (level1 == null) {
            return ResponseMessage.badRequest("Level not created");
        } else {
            return ResponseMessage.created(LevelResponseDTO.fromLevel(level1), "Level created successfully");
        }
    }

    // update level
    @PutMapping("/{id}")
    public ResponseEntity updateLevel(@Valid @RequestBody LevelRequestDTO levelRequestDTO, @PathVariable Long id) {
        Level level1 = levelService.updateLevel(levelRequestDTO.toLevel(), id);
        if (level1 == null) {
            return ResponseMessage.badRequest("Level not updated");
        } else {
            return ResponseMessage.created(LevelResponseDTO.fromLevel(level1), "Level updated successfully");
        }
    }

    // delete level
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLevel(@PathVariable Long id) {
            levelService.deleteLevel(id);
            return ResponseMessage.ok(null,"Level deleted successfully");
        }
    }
}
