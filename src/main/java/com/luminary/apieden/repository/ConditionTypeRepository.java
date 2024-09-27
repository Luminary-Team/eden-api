package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.ConditionTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionTypeRepository extends JpaRepository<ConditionTypes, Long> {
}
