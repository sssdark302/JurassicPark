package com.example.jurassicpark.models;

public abstract class Dinosaurio {
    public String especie;
    public int edad;
    public double altura_maxima;
    public int peso_maximo;
    public char Sexo;
    public String tipo;
    public double hp_maxima;

    public Dinosaurio(String especie, int edad, double alturaMaxima, int pesoMaximo, char sexo, double hpMaxima) {
        this.especie = especie;
        this.edad = edad;
        this.altura_maxima = altura_maxima;
        this.peso_maximo = peso_maximo;
        this.Sexo = Sexo;
        this.tipo = tipo;
        this.hp_maxima = hp_maxima;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "Dinosaurio{" +
                "especie='" + especie + '\'' +
                ", edad=" + edad +
                ", altura_maxima=" + altura_maxima +
                ", peso_maximo=" + peso_maximo +
                ", Sexo=" + Sexo +
                ", tipo='" + tipo + '\'' +
                ", hp_maxima=" + hp_maxima +
                '}';
    }
}

