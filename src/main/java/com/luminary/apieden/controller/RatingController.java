package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.RatingContract;
import com.luminary.apieden.model.database.Rating;
import com.luminary.apieden.model.request.RatingRequest;
import com.luminary.apieden.model.request.UpdateRatingRequest;
import com.luminary.apieden.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController implements RatingContract {
    private final RatingService ratingService;

    @GetMapping("")
    public ResponseEntity<Rating> getRating(@RequestParam("userAppraiserId") String userAppraiserId,
                                            @RequestParam("userAppraisedId") String userAppraisedId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRating(
                userAppraiserId,
                userAppraisedId)
        );
    }

    @PostMapping("")
    public ResponseEntity<Rating> register(@RequestBody @Valid RatingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.register(request));
    }

    @PatchMapping("")
    public ResponseEntity<Rating> update(
            @RequestParam("userAppraiserId") String userAppraiserId,
            @RequestParam("userAppraisedId") String userAppraisedId,
            @RequestBody UpdateRatingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.update(
                userAppraiserId,
                userAppraisedId,
                request));
    }
}
