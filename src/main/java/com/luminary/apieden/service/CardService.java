package com.luminary.apieden.service;

import com.luminary.apieden.mapper.CardMapper;
import com.luminary.apieden.model.database.Card;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.CardRequest;
import com.luminary.apieden.model.response.CardResponse;
import com.luminary.apieden.repository.CardRepository;
import com.luminary.apieden.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;

    public List<CardResponse> findByUserId(User userId) {
        List<Card> cardList = cardRepository.findByUser(userId);
        return cardList.stream()
                .map(cardMapper::toCardResponse)
                .toList();
    }

    public Card registerCard(CardRequest cardRequest) {
        log.info("[CARDS] Registering card");
        log.info("[CARDS] Finding user in database");
        User user = userRepository.findById(cardRequest.getUserId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "User not found"));
        log.info("[CARDS] Found user");
        log.info("[CARDS] Prepared card");
        Card cards = cardMapper.toCards(cardRequest);
        cards.setUser(user);
        log.info("[CARDS] Card prepared");
        log.info("[CARDS] Persisting card in database");
        cardRepository.save(cards);
        log.info("[CARDS] Card persisted in database");
        return cards;
    }

    public void deleteCard(String id) {
        cardRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Card not found"));
        cardRepository.deleteById(Long.valueOf(id));
    }
}
