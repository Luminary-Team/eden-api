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

    public List<CardResponse> findByUserId(String userId) {
        try {
            List<Card> cardList = cardRepository.findById(Long.parseLong(userId));
            return cardList.stream()
                    .map(cardMapper::toCardResponse)
                    .toList();
        } catch (NullPointerException npe) {
            return List.of();
        }
    }

    public Card registerCard(CardRequest cardRequest) {
        log.info("[CARDS] Registering card");

        // Validação do CVV | CVC | Validade
        if (cardRequest.getCvv().length() != 3) {
            log.error("[CARDS] CVV com comprimento inválido");
            throw new HttpError(HttpStatus.BAD_REQUEST, "CVV deve conter exatamente 3 caracteres.");
        } else if (cardRequest.getCvc().length() != 3) {
            log.error("[CARDS] CVC com comprimento inválido");
            throw new HttpError(HttpStatus.BAD_REQUEST, "CVC deve conter exatamente 3 caracteres.");
        } else if (cardRequest.getValidity().length() != 5) {
            log.error("[CARDS] Validade com comprimento inválido");
            throw new HttpError(HttpStatus.BAD_REQUEST, "Validade deve conter exatamente 5 caracteres.");
        }

        log.info("[CARDS] Finding user in database");
        User user = userRepository.findById(cardRequest.getUserId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));

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
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Cartão não encontrado"));

        log.info("[CARDS] Deleting card");
        cardRepository.deleteById(Long.valueOf(id));
    }
}