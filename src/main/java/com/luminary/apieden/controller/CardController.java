package com.luminary.apieden.controller;

import com.luminary.apieden.model.database.Card;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.request.CardRequest;
import com.luminary.apieden.model.response.CardResponse;
import com.luminary.apieden.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

//    @GetMapping("/user/{id}")
//    public ResponseEntity<List<CardResponse>> findByUserId(@PathVariable User user) {
//        return ResponseEntity.status(HttpStatus.OK).body(cardService.findByUserId(user));
//    }

    @PostMapping("/")
    public ResponseEntity<Card> register(@RequestBody @Valid CardRequest cardRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.registerCard(cardRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id) {
        cardService.deleteCard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
