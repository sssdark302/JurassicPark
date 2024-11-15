package com.example.jurassicpark.controllers;
import com.example.jurassicpark.exceptiones.DinosaurioNotFoundException;
import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.models.Dinosaurio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dinosaurios")
public class DinosaurioController {

    @Autowired
    private DinosaurioRepository dinosaurioRepository;

    @GetMapping("/todosdinosaurios")
    public String getAllDinosaurios() {
        DinosaurioDataStore dataStore = DinosaurioDataStore.getInstance();
        return dataStore.getAllDinosauriosAsJSON();
    }

    @GetMapping("/{especie}")
    public Dinosaurio getDinosaurioByEspecie(@PathVariable String especie) {
        Dinosaurio dinosaurio = dinosaurioRepository.findDinosaurioByEspecie(especie);
        if (dinosaurio != null) {
            return dinosaurio;
        } else {
            throw new DinosaurioNotFoundException("Dinosaurio con especie " + especie + " no encontrado");
        }
    }

    @GetMapping("/tipo/{tipo}")
    public Dinosaurio getDinosaurioByTipo(@PathVariable String tipo) {
        Dinosaurio dinosaurio = dinosaurioRepository.findDinosaurioByTipo(tipo);
        if (dinosaurio != null) {
            return dinosaurio;
        } else {
            throw new DinosaurioNotFoundException("Dinosaurio con tipo " + tipo + " no encontrado");
        }
    }
}
