package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporalInstalacionRepository extends JpaRepository <InstalacionE, Integer> {

    void deleteAll();

    @Query(value = "INSERT INTO temporal_instalaciones (nombre, tipo, capacidad) VALUES (:nombre, :tipo, :capacidad)", nativeQuery = true)
    void guardarInstalacionTemporal(@Param("nombre") String nombre,
                                    @Param("tipo") String tipo,
                                    @Param("capacidad") int capacidad);

    @Query(value = "SELECT * FROM temporal_instalaciones WHERE tipo = :tipo", nativeQuery = true)
    List<InstalacionE> obtenerInstalacionesPorTipo(@Param("tipo") String tipo);

    @Query(value = "DELETE FROM temporal_instalaciones", nativeQuery = true)
    void limpiarInstalacionesTemporales();
}
