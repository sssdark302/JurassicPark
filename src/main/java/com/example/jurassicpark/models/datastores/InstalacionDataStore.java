package com.example.jurassicpark.models.datastores;

import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.service.InstalacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InstalacionDataStore {

    private final List<InstalacionE> instalaciones = new ArrayList<>();

    @Autowired
    @Lazy
    private InstalacionService instalacionService;

    @PostConstruct
    public void init() {
        String rutaCSV = "data/datos-instalaciones.csv";
        instalacionService.cargarDatosCSV(rutaCSV);
    }

    public List<InstalacionE> getInstalaciones() {
        return new ArrayList<>(instalaciones);
    }

    public List<InstalacionE> getInstalacionesPorTipo(String tipo) {
        return instalaciones.stream()
                .filter(instalacion -> instalacion.getTipo().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    public String getAllInstalacionesAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(instalaciones);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public void agregarInstalacion(InstalacionE instalacion) {
        instalaciones.add(instalacion); // Agregar la instalación a la lista en memoria
    }

    public void eliminarInstalacion(InstalacionE instalacion) {
        instalaciones.remove(instalacion); // Eliminar la instalación de la lista en memoria
    }
}
