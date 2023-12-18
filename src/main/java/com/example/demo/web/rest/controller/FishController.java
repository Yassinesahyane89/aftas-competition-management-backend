package com.example.demo.web.rest.controller;

import com.example.demo.entity.Fish;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.FishService;
import com.example.demo.web.DTO.response.FishResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fishes")
@CrossOrigin("*")
public class FishController {
    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public ResponseEntity getAllFishes() {
        List<Fish> fishes = fishService.getAllFishes();
        if(fishes.isEmpty()) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            List<FishResponseDTO> fishResponseDTOS = new ArrayList<>();
            for(Fish fish : fishes) {
                fishResponseDTOS.add(FishResponseDTO.fromFish(fish));
            }
            return ResponseMessage.ok(fishResponseDTOS, "Success");
        }
    }
}
