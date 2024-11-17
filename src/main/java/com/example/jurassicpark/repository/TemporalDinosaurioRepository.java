package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporalDinosaurioRepository extends JpaRepository<Dinos, Integer > {
    void deleteAll();

    @Query(value = "INSERT INTO temporal_dinosaurios (especie, tipo, edad) VALUES (:especie, :tipo, :edad)", nativeQuery = true)
    void guardarDinosaurioTemporal(@Param("especie") String especie,
                                   @Param("tipo") String tipo,
                                   @Param("edad") int edad);

    @Query(value = "SELECT * FROM temporal_dinosaurios WHERE tipo = :tipo", nativeQuery = true)
    List<Dinos> obtenerDinosauriosPorTipo(@Param("tipo") String tipo);

    @Query(value = "DELETE FROM temporal_dinosaurios", nativeQuery = true)
    void limpiarDinosauriosTemporales();
}
