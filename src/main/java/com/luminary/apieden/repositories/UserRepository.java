package com.luminary.apieden.repositories;

import com.luminary.apieden.models.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByCpf(String cpf);
    List<User> findByPhone(String phone);
    List<User> findByEmail(String email);
}
