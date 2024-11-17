package com.example.jurassicpark.service;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.repository.TemporalDinosaurioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


