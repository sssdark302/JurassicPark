package com.example.jurassicpark.repository;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.DinosaurioDataStore;

import java.util.Collection;

public class DinosaurioRepository {
    private DinosaurioDataStore dataStore;

    public DinosaurioRepository() {
        dataStore = DinosaurioDataStore.getInstance();
    }

    public static Dinosaurio getDinosaurioByEspecie(String especie) {
        return DinosaurioDataStore.getDinosaurio(especie);
    }
}



