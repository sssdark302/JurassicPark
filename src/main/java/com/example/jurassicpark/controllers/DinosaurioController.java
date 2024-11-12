package com.example.jurassicpark.controllers;
import com.example.jurassicpark.exceptiones.DinosaurioNotFoundException;
import com.example.jurassicpark.models.DinosaurioDataStore;
import com.example.jurassicpark.models.DinosaurioFactory;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.models.Dinosaurio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dinosaurios")
public class DinosaurioController {

    @GetMapping("/todosdinosaurios")
    public String getAllDinosaurios() {
        DinosaurioDataStore dataStore = DinosaurioDataStore.getInstance();
        return dataStore.getAllDinosauriosAsJSON();
    }

    @GetMapping("/{especie}")
    public Dinosaurio getDinosaurioByEspecie(@PathVariable String especie) {
        Dinosaurio dinosaurio = DinosaurioRepository.getDinosaurioByEspecie(especie);
        if (dinosaurio != null) {
            return dinosaurio;
        } else {
            throw new DinosaurioNotFoundException("Dinosaurio con especie " + especie + " no encontrado");
        }
    }
}
