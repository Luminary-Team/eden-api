package com.luminary.apieden.repositories;

import com.luminary.apieden.models.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
    User findByEmail(String email);
    User findByUsername(String username);
}
