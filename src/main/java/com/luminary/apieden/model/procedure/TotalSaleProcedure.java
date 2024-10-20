package com.luminary.apieden.model.procedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import org.springframework.stereotype.Repository;

@Repository
public class TotalSaleProcedure {
    @PersistenceContext
    EntityManager entityManager;

    public void totalSale(int cartId) {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("total_sale_calc");
        storedProcedure.registerStoredProcedureParameter("cart_id", Integer.class,
                ParameterMode.IN);
        storedProcedure.setParameter("cart_id", cartId);
        storedProcedure.execute();
    }
}
