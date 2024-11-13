package com.example.jurassicpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jurassicpark.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Método necesario para la autenticación
    User findByUsername(String username);
}
