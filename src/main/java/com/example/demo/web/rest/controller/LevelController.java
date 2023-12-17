package com.example.demo.web.rest.controller;

import com.example.demo.entity.Level;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseMessage.ok(levels, "Success");
        }
    }
}
