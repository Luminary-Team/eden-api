package com.luminary.apieden.controller;

import com.luminary.apieden.model.database.Rating;
import com.luminary.apieden.model.request.RatingRequest;
import com.luminary.apieden.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/a")
    public ResponseEntity<Rating> register(@RequestBody RatingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.register(request));
    }
}
