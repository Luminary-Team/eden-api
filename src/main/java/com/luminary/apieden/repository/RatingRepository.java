package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
