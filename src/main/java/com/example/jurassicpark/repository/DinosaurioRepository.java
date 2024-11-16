package com.example.jurassicpark.repository;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DinosaurioRepository extends JpaRepository<Dinos,Integer> {

    Dinosaurio findDinosaurioByEspecie(String especie);
    Dinosaurio findDinosaurioByTipo(String tipo);
    Dinosaurio findDinosaurioById(int id);
    @Query("SELECT d FROM Dinos d WHERE d.faseCicloDeVida = :fase AND d.especie = :especie")
    List<Dinos> findByFaseCicloDeVidaAndEspecie(@Param("fase") FaseCicloDeVida fase, @Param("especie") String especie);

}



