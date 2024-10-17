package com.luminary.apieden.procedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserRatingProcedure {
    @PersistenceContext
    EntityManager entityManager;

    public void userRating(int userId) {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("user_rating_calc");
        storedProcedure.registerStoredProcedureParameter("user_id", Integer.class,
                ParameterMode.IN);
        storedProcedure.setParameter("user_id", userId);
        storedProcedure.execute();
    }
}

