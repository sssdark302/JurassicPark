package com.example.jurassicpark.models;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Sexo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dinosaurio {

    // Getters y Setters
    protected String especie;
    protected int edad;
    protected double altura_maxima;
    protected int peso_maximo;
    protected double hp_maxima;
    protected Sexo sexo;
    protected boolean tuvoHijos;
    protected String tipo; // Dieta
    protected FaseCicloDeVida faseCicloDeVida;
    protected String habitat;

    public Dinosaurio(String especie, int edad, double altura_maxima, int peso_maximo, Sexo sexo, double hp_maxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, String habitat, String tipo) {
        if (altura_maxima < 0 || peso_maximo < 0 || hp_maxima < 0) { //cambiarlo cuando la fase de vida este completada
            throw new IllegalArgumentException("Los valores de altura, peso y hp deben ser positivos.");
        }
        this.especie = especie;
        this.edad = edad;
        this.altura_maxima = altura_maxima;
        this.peso_maximo = peso_maximo;
        this.hp_maxima = hp_maxima;
        this.sexo = sexo;
        this.tuvoHijos = tuvoHijos;
        this.faseCicloDeVida = faseCicloDeVida;
        this.habitat = habitat;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Dinosaurio{" +
                "especie='" + especie + '\'' +
                ", edad=" + edad +
                ", altura_maxima=" + altura_maxima +
                ", peso_maximo=" + peso_maximo +
                ", hp_maxima=" + hp_maxima +
                ", sexo=" + sexo +
                ", tuvoHijos=" + tuvoHijos +
                ", tipo='" + tipo + '\'' +
                ", faseCicloDeVida=" + faseCicloDeVida +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}
