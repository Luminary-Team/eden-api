package com.luminary.apieden.repositories;

import com.luminary.apieden.models.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);
    Optional<User> findByCellphone(String phone);
}
