package com.example.jurassicpark.models;

public class DinosaurioFactory {
    private DinosaurioDataStore dataStore;

    public static Dinosaurio crearDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo, Sexo sexo, double hpMaxima, boolean tuvoHijos) {
        switch (tipo) {
            case "Carnivoro":
                return new Carnivoro(especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos);
            case "Herbivoro":
                return new Herbivoro(especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos);
            case "Omnivoro":
                return new Omnivoro(especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos);
            default:
                throw new IllegalArgumentException("Tipo de dinosaurio desconocido: " + tipo);
        }
    }
}

