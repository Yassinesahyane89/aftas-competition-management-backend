package com.example.demo.web.rest.controller;

import com.example.demo.entity.Fish;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.FishService;
import com.example.demo.web.DTO.request.FishRequestDTO;
import com.example.demo.web.DTO.response.FishResponseDTO;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity getFishById(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if(fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok(FishResponseDTO.fromFish(fish), "Success");
        }
    }

    @PostMapping
    public ResponseEntity addFish(@Valid @RequestBody FishRequestDTO fishRequestDTO) {
        Fish newFish = fishService.addFish(fishRequestDTO.toFish());
        if(newFish == null) {
            return ResponseMessage.badRequest("Fish not created");
        }else {
            return ResponseMessage.created(FishResponseDTO.fromFish(newFish), "Fish created successfully");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFish(@RequestBody Fish fish, @PathVariable Long id) {
        Fish updatedFish = fishService.updateFish(fish, id);
        if(updatedFish == null) {
            return ResponseMessage.badRequest("Fish not updated");
        }else {
            return ResponseMessage.created(FishResponseDTO.fromFish(updatedFish), "Fish updated successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFish(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if(fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            fishService.deleteFish(id);
            return ResponseMessage.ok(null,"Fish deleted successfully");
        }
    }
}
