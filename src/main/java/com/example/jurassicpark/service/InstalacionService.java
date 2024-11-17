package com.example.jurassicpark.service;

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
    private TemporalInstalacionRepository temporalInstalacionRepository;


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

    /*
    @PostConstruct
    public void inicializarInstalacionesPorDefecto() {
        if (instalacionRepository.count() == 0) {
            // Creación de instalaciones predeterminadas
            InstalacionE centroVisitantes = new InstalacionE();
            centroVisitantes.setNombre("Centro de Visitantes");
            centroVisitantes.setCapacidad(100);
            centroVisitantes.setTerreno(500.0);
            centroVisitantes.setSeguridad("Alta");
            centroVisitantes.setDescripcion("Centro de interacción con visitantes");
            centroVisitantes.setPersonal(10);
            centroVisitantes.setHorario("9:00-18:00");
            centroVisitantes.setTipo("Centro");

            InstalacionE enfermeria = new InstalacionE();
            enfermeria.setNombre("Enfermería");
            enfermeria.setCapacidad(50);
            enfermeria.setTerreno(200.0);
            enfermeria.setSeguridad("Media");
            enfermeria.setDescripcion("Centro de atención para dinosaurios");
            enfermeria.setPersonal(5);
            enfermeria.setHorario("8:00-17:00");
            enfermeria.setTipo("Sanitario");

            InstalacionE laboratorioGenetica = new InstalacionE();
            laboratorioGenetica.setNombre("Laboratorio de Genética");
            laboratorioGenetica.setCapacidad(20);
            laboratorioGenetica.setTerreno(300.0);
            laboratorioGenetica.setSeguridad("Alta");
            laboratorioGenetica.setDescripcion("Investigación genética de dinosaurios");
            laboratorioGenetica.setPersonal(15);
            laboratorioGenetica.setHorario("9:00-18:00");
            laboratorioGenetica.setTipo("Científico");

            // Guardar instalaciones en la base de datos
            guardarInstalacion(centroVisitantes);
            guardarInstalacion(enfermeria);
            guardarInstalacion(laboratorioGenetica);
        }
    }
     */


}
