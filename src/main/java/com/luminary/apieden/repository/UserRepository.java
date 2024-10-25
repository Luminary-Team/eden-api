package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
    Optional<User> findByCellphone(String phone);

    @Modifying
    @Transactional
    @Query(value = "CALL user_rating_calc(:user_id)", nativeQuery = true)
    void userRating(@Param("user_id") int userId);
}
