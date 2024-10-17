package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findRatingByUserAppraiserIdAndUserAppraisedId(
            long userAppraiserId, long userAppraisedId);
}
