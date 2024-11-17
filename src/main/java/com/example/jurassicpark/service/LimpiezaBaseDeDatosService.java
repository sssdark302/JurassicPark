package com.example.jurassicpark.service;

import com.example.jurassicpark.repository.TemporalDinosaurioRepository;
import com.example.jurassicpark.repository.TemporalInstalacionRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimpiezaBaseDeDatosService {

    @Autowired
    private TemporalDinosaurioRepository temporalDinosaurioRepository;

    @Autowired
    private TemporalInstalacionRepository temporalInstalacionRepository;

    @PreDestroy
    public void limpiarBaseDeDatos() {
        temporalDinosaurioRepository.deleteAll();
        temporalInstalacionRepository.deleteAll();
        System.out.println("Base de datos temporal truncada.");
    }
}
