package com.example.jurassicpark.service;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.repository.TemporalDinosaurioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinosaurioService {

    @Autowired
    @Lazy
    private DinosaurioRepository dinosaurioRepository;

    @Autowired
    @Lazy
    private TemporalDinosaurioRepository temporalDinosaurioRepository;

    public List<Dinos> obtenerDinosauriosOGPorTipo(String tipo) {
        return dinosaurioRepository.findByTipo(tipo);
    }

    public void guardarDinosaurioTemporal(Dinos dinosaurio) {
        temporalDinosaurioRepository.save(dinosaurio);
    }

    public void limpiarDinosauriosTemporales() {
        temporalDinosaurioRepository.deleteAll();
    }


}


