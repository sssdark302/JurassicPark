package com.example.jurassicpark.models;
public class Carnivoro extends Dinosaurio {
    public Carnivoro(String especie, int edad, double alturaMaxima, int pesoMaximo, char sexo, double hp_maxima) {
        super(especie, edad, alturaMaxima, pesoMaximo, sexo, hp_maxima);
    }

    @Override
    public String getTipo() {
        return "Carnivoro";
    }
}


