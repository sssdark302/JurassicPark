package com.example.jurassicpark.models;

public class Herbivoro extends Dinosaurio{
    public Herbivoro(String especie, int edad, double alturaMaxima, int pesoMaximo, Sexo sexo, double hp_maxima) {
        super(especie, edad, alturaMaxima, pesoMaximo, sexo, hp_maxima);
    }

    @Override
    public String getTipo() {
        return "Herbivoro";
    }
}
