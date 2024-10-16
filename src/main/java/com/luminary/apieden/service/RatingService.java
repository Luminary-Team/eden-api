package com.luminary.apieden.service;

import com.luminary.apieden.mapper.RatingMapper;
import com.luminary.apieden.model.database.Rating;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.RatingRequest;
import com.luminary.apieden.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public Rating register(RatingRequest request) {
        if (request.getUserAppraiserId() == 0
        || request.getUserAppraisedId() == 0
        || request.getRating() == 0) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Valores não podem ser 0");
        }
        Rating rating = ratingMapper.toRating(request);
        ratingRepository.save(rating);
        return rating;
    }

    public Rating update(long id, float newRating) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Avaliação não encontrado"));
        rating.setRating(newRating);
        ratingRepository.save(rating);
        return rating;
    }
}
