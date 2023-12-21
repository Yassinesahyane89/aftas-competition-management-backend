package com.example.demo.web.rest.controller;

import com.example.demo.entity.Hunting;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.HuntingService;
import com.example.demo.web.DTO.request.HuntingRequestDTO;
import com.example.demo.web.DTO.response.HuntingResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hunting")
@CrossOrigin("*")
public class HuntingController {
    private final HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    // add hunting result
    @PostMapping("/add-hunting-result")
    public ResponseEntity<?> addHuntingResult(@Valid @RequestBody HuntingRequestDTO huntingRequestDTO) {
        Hunting hunting = huntingService.addHuntingResult(huntingRequestDTO.toHunting());
        if(hunting == null) {
            return ResponseMessage.badRequest("Hunting not created");
        }else {
            return ResponseMessage.ok(null, "Success");
        }
    }
}
