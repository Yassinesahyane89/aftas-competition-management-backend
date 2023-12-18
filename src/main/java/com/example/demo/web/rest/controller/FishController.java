package com.example.demo.web.rest.controller;

import com.example.demo.service.FishService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fishes")
@CrossOrigin("*")
public class FishController {
    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
}
