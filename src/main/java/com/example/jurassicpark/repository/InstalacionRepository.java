package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstalacionRepository extends JpaRepository<InstalacionE, String> {

    InstalacionE findInstalacionByNombre(String nombre);
    InstalacionE findByHabitatAndTipoDieta(String habitat, String dieta);
    void deleteInstalacionByTipo(String tipo);
    List<InstalacionE> findByTipo(String tipo);
    List<InstalacionE> findByHabitat(String habitat);
}
