package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DinosaurioInstalacionRepository extends JpaRepository <DinosaurioInstalaciones, Integer>{

    Optional<DinosaurioInstalaciones> findByDinosaurio(Dinos dinosaurio);
    List<DinosaurioInstalaciones> findByInstalacion(InstalacionE instalacion);

}
