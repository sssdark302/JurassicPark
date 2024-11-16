package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstalacionRepository extends JpaRepository<InstalacionE, String> {

    InstalacionE findInstalacionByNombre(String nombre);
    Optional<InstalacionE> findByHabitatAndDieta(String habitat, String dieta);
    void deleteInstalacionByTipo(String tipo);
    List<InstalacionE> findByTipo(String tipo);
    List<InstalacionE> findByHabitat(String habitat);
}
