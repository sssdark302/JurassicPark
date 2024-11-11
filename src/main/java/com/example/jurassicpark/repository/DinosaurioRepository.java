package com.example.jurassicpark.repository;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.DinosaurioDataStore;

import java.util.Collection;

public class DinosaurioRepository {
    private DinosaurioDataStore dataStore;

    public DinosaurioRepository() {
        dataStore = DinosaurioDataStore.getInstance();
    }

    public void mostrarDinosaurio(Dinosaurio dinosaurio) {
        if (dinosaurio != null) {
            System.out.println(dinosaurio);
        } else {
            System.out.println("Dinosaurio no encontrado.");
        }
    }

    public static Dinosaurio getDinosaurioByEspecie(String especie) {
        return DinosaurioDataStore.getDinosaurio(especie);
    }

    public void mostrarDinosaurios(Collection<Dinosaurio> dinosaurios) {
        if (dinosaurios.isEmpty()) {
            System.out.println("No hay dinosaurios disponibles.");
        } else {
            for (Dinosaurio dinosaurio : dinosaurios) {
                System.out.println(dinosaurio);
            }
        }
    }
}



