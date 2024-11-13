package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.DinosaurioFactory;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinosaurioRepository extends JpaRepository<Dinos,Integer> {

    Dinosaurio findDinosaurioByEspecie(String especie);
    Dinosaurio findDinosaurioByTipo(String tipo);
    Dinosaurio findDinosaurioById(int id);
}



