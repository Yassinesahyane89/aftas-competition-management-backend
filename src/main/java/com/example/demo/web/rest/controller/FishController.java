package com.example.demo.web.rest.controller;

import com.example.demo.entity.Fish;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.FishService;
import com.example.demo.web.DTO.response.FishResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity getFishById(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if(fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok(FishResponseDTO.fromFish(fish), "Success");
        }
    }

}
