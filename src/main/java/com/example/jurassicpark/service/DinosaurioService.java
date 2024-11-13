package com.example.jurassicpark.service;

import com.example.jurassicpark.models.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.repository.DinosaurioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DinosaurioService {

    @Autowired
    private DinosaurioRepository dinosaurioRepository;

    @Autowired
    private DinosaurioFactory dinosaurioFactory;

    public Dinos agregarDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo, Sexo sexo, double hpMaxima, boolean tuvoHijos) {
        Dinos dinosaurio = dinosaurioFactory.crearDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos);
        return dinosaurioRepository.save(dinosaurio);
    }

    public void eliminarDinosaurio(Dinos dinosaurio) {
        dinosaurioRepository.delete(dinosaurio);
    }
}
