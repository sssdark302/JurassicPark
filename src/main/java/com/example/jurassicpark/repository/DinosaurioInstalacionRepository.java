package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DinosaurioInstalacionRepository extends JpaRepository {

    List<DinosaurioInstalaciones> findByInstalacion(InstalacionE instalacion);
    List<DinosaurioInstalaciones> findByDinosaurio(Dinos dinosaurio);
}