package com.luminary.apieden.model.procedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import org.springframework.stereotype.Repository;

@Repository
public class TotalSaleProcedure {
    @PersistenceContext
    EntityManager entityManager;

    public float totalSale(int orderId) {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("total_sale_calc");
        storedProcedure.registerStoredProcedureParameter("order_id", Integer.class,
                ParameterMode.IN);
        storedProcedure.setParameter("order_id", orderId);
        storedProcedure.execute();

        Query query = entityManager.createQuery(
                "SELECT o.totalSale FROM Order o WHERE o.id = :orderId");
        query.setParameter("orderId", orderId);

        return (Float) query.getSingleResult();
    }
}
