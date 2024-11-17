package com.example.jurassicpark.models.datastores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.service.DinosaurioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DinosaurioDataStore {

    private final List<Dinos> dinosaurios = new ArrayList<>();

    @Autowired
    @Lazy
    private DinosaurioService dinosaurioService;

    @PostConstruct
    public void init() {
        dinosaurios.clear();
        dinosaurioService.cargarDatosCSV("data/datos-dinos.csv");
        dinosaurios.addAll(dinosaurioService.listarDinosaurios());
    }

    public List<Dinos> getDinosaurios() {
        return new ArrayList<>(dinosaurios);
    }

    public List<Dinos> getDinosauriosCompatibles(String tipoInstalacion) {
        return dinosaurios.stream()
                .filter(dino -> dino.getTipo().equalsIgnoreCase(tipoInstalacion))
                .collect(Collectors.toList());
    }

    public String getAllDinosauriosAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dinosaurios);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public void agregarDinosaurio(Dinos dinosaurio) {
        dinosaurios.add(dinosaurio); // Actualizar la lista en memoria
    }

    public void eliminarDinosaurio(Dinos dinosaurio) {
        dinosaurios.remove(dinosaurio); // Eliminar el dinosaurio de la lista en memoria
    }
}
