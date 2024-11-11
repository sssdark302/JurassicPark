package com.example.jurassicpark.models;

public class Omnivoro extends Dinosaurio{
    public Omnivoro(String especie, int edad, double alturaMaxima, int pesoMaximo, char sexo, double hp_maxima) {
        super(especie, edad, alturaMaxima, pesoMaximo, sexo, hp_maxima);
    }

    @Override
    public String getTipo() {
        return "Omnivoro";
    }
}
