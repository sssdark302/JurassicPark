package com.example.jurassicpark.controllers;
import com.example.jurassicpark.controllers.exceptiones.DinosaurioNotFoundException;
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
    private DinosaurioFactory factory;
    private DinosaurioRepository view;

    public DinosaurioController() {
        factory = new DinosaurioFactory();
        view = new DinosaurioRepository();
    }

    public void mostrarDinosaurio(String especie) {
        Dinosaurio dinosaurio = factory.obtenerDinosaurio(especie);
        if (dinosaurio != null) {
            view.mostrarDinosaurio(dinosaurio);
        } else {
            System.out.println("Dinosaurio no encontrado.");
        }
    }

    @GetMapping
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
