package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.Instalacion;
import com.example.jurassicpark.models.InstalacionDataStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalacionRepository extends JpaRepository<Instalacion, String> {
    //buscar por id, nombre, tipo
    Instalacion findInstalacionById(int id);
    Instalacion findInstalacionByNombre(String nombre);
    Instalacion findInstalacionByTipo(String tipo);
}
