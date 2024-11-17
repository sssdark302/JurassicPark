package com.example.jurassicpark.repository;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DinosaurioRepository extends JpaRepository<Dinos,Integer> {

    @Query("SELECT d FROM Dinos d WHERE d.tipo = :tipo")
    List<Dinos> findByTipo(@Param("tipo") String tipo);

    @Query("SELECT d FROM Dinos d WHERE d.faseCicloDeVida = :fase AND d.especie = :especie")
    List<Dinos> findByFaseCicloDeVidaAndEspecie(@Param("fase") FaseCicloDeVida fase, @Param("especie") String especie);;
}



