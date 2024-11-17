package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstalacionRepository extends JpaRepository<InstalacionE, String> {
    List<InstalacionE> findByTipo(String tipo);
    Optional<InstalacionE> findByNombre(String nombre);
}