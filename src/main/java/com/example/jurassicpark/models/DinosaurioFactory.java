package com.example.jurassicpark.models;

public class DinosaurioFactory {
    private DinosaurioDataStore dataStore;

    public DinosaurioFactory() {
        dataStore = DinosaurioDataStore.getInstance();
    }

    public Dinosaurio obtenerDinosaurio(String especie) {
        return dataStore.getDinosaurio(especie);
    }
}

