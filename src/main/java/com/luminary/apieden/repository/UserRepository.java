package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
    Optional<User> findByCellphone(String phone);
}
