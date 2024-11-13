package com.example.jurassicpark.models;

import com.example.jurassicpark.models.entidades.Dinos;

public class Omnivoro extends Dinos {

    public Omnivoro(String especie, int edad, double altura_maxima, int peso_maximo, Sexo sexo, double hp_maxima, boolean tuvoHijos) {
        super(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos);
    }

    @Override
    public String getTipo() {
        return "Omnivoro";
    }
}
