package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.Instalacion;
import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalacionRepository extends JpaRepository<Instalacion, String> {
    //buscar por id, nombre, tipo
    InstalacionE findByHabitatAndTipoDieta(String habitat, String tipoDieta);
    InstalacionE findInstalacionById(int id);
    InstalacionE findInstalacionByNombre(String nombre);
    InstalacionE findInstalacionByTipo(String tipo);
}
