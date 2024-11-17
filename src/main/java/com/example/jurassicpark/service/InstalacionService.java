package com.example.jurassicpark.service;

import com.example.jurassicpark.models.InstalacionRequest;
import com.example.jurassicpark.models.factorias.InstalacionFactory;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import com.example.jurassicpark.repository.TemporalInstalacionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstalacionService {

    @Autowired
    @Lazy
    private InstalacionRepository instalacionRepository;

    @Autowired
    @Lazy
    private TemporalInstalacionRepository temporalInstalacionRepository;

    @Autowired
    private InstalacionFactory instalacionFactory;

    @PostConstruct
    public void inicializarInstalacionesPorDefecto() {
        if (instalacionRepository.count() > 0) {
            return; // Si ya hay instalaciones en la base de datos, no inicializamos
        }

        // Crear instalaciones predeterminadas usando la factoría
        instalacionFactory.crearInstalacion(
                "Centro de Visitantes",
                100,
                500.0,
                "Alta",
                "Centro de interacción con visitantes",
                10,
                "9:00-18:00",
                "Turismo"
        );

        instalacionFactory.crearInstalacion(
                "Enfermería",
                50,
                200.0,
                "Media",
                "Centro de atención para dinosaurios",
                5,
                "8:00-17:00",
                "Instalacion_Islas"
        );

        instalacionFactory.crearInstalacion(
                "Laboratorio de Genética",
                20,
                300.0,
                "Alta",
                "Investigación genética de dinosaurios",
                15,
                "9:00-18:00",
                "Instalacion_Islas"
        );

        System.out.println("Instalaciones predeterminadas inicializadas correctamente.");
    }

    public InstalacionE crearInstalacion(InstalacionRequest request) {
        return instalacionFactory.crearInstalacion(
                request.getNombre(),
                request.getCapacidad(),
                request.getTerreno(),
                request.getSeguridad(),
                request.getDescripcion(),
                request.getPersonal(),
                request.getHorario(),
                request.getTipo()
        );
    }

    public InstalacionE obtenerInstalacionOriginalPorNombre(String nombre) {
        return instalacionRepository.findByNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la instalación con el nombre: " + nombre));
    }

    public void guardarInstalacionTemporal(InstalacionE instalacion) {
        temporalInstalacionRepository.save(instalacion);
    }

    public void limpiarInstalacionesTemporales() {
        temporalInstalacionRepository.deleteAll();
    }

    public List<InstalacionE> obtenerTodasLasInstalaciones() {
        return instalacionRepository.findAll();
    }

}
