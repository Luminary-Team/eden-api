package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.UsageTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageTimeRepository extends JpaRepository<UsageTime, Long> {
}
