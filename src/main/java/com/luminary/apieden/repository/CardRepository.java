package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Card;
import com.luminary.apieden.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findById(long userId);
}
