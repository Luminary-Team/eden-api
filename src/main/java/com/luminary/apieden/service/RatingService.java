package com.luminary.apieden.service;

import com.luminary.apieden.mapper.RatingMapper;
import com.luminary.apieden.model.database.Rating;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.RatingRequest;
import com.luminary.apieden.model.request.UpdateRatingRequest;
import com.luminary.apieden.repository.RatingRepository;
import com.luminary.apieden.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public Rating getRating(String userAppraiserId,
                            String userAppraisedId) {
        return ratingRepository.findRatingByUserAppraiserIdAndUserAppraisedId(
                Long.parseLong(userAppraiserId),
                Long.parseLong(userAppraisedId))
                    .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Nenhum 'rating' existente"));
    }

    public Rating register(RatingRequest request) {
        log.info("[Rating Service] Starting param validations");
        verifyRatingRequest(request);
        log.info("[Rating Service] Parameters validated");
        Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAppraiserIdAndUserAppraisedId(
                request.getUserAppraiserId(),
                request.getUserAppraisedId()
        );
        if (ratingOptional.isPresent()) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Avaliação já realizada, tente atualiza-la");
        }
        Rating rating = ratingMapper.toRating(request);
        ratingRepository.save(rating);
        log.info("[Rating Service] Saved user in database");
        userRepository.userRating((int) rating.getUserAppraisedId());
        log.info("[Rating Service] Called procedure");
        return rating;
    }

    public Rating update(
            String userAppraiserId,
            String userAppraisedId,
            UpdateRatingRequest request) {
        log.info("[Rating Service] update rating starting");
        if (request.getRating() < 0.5 && request.getRating() > 5) {
            log.error("[Rating Service] Rating is invalid {}", request.getRating());
            throw new HttpError(HttpStatus.BAD_REQUEST, "'rating' não pode ser menor que 0.5");
        }
        if (userAppraiserId.equals(userAppraisedId)) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Avaliador não pode se avaliar");
        }
        Rating rating = ratingRepository.findRatingByUserAppraiserIdAndUserAppraisedId(
                    Long.parseLong(userAppraiserId),
                    Long.parseLong(userAppraisedId))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Avaliação não encontrada"));
        log.info("[Rating Service] Rating founded");
        rating.setRating(request.getRating());
        log.info("[Rating Service] Setting rating");
        ratingRepository.save(rating);
        log.info("[Rating Service] Saved in database");
        userRepository.userRating((int) rating.getUserAppraisedId());
        log.info("[Rating Service] Procedure called");
        return rating;
    }

    public void verifyRatingRequest(RatingRequest request) {
        if (
                request.getUserAppraiserId() == 0
                || request.getUserAppraisedId() == 0
                || request.getRating() == 0
        ) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Valores precisam ser passados explicitamente(não podem ser 0)");
        } else if (request.getUserAppraiserId() == request.getUserAppraisedId()) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "Avaliador não pode se avaliar");
        } else if (request.getRating() < 0.5 || request.getRating() > 5) {
            throw new HttpError(HttpStatus.BAD_REQUEST, "'rating' precisa estar entre 0.5 e 5");
        }
    }
}
