package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
