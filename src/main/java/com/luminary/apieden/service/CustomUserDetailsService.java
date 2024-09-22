package com.luminary.apieden.service;

import com.luminary.apieden.model.database.User;
import com.luminary.apieden.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository usersRepository;

    public CustomUserDetailsService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não existe"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
