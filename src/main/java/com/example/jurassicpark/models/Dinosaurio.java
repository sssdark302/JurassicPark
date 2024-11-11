package com.example.jurassicpark.models;

public abstract class Dinosaurio {

    public String especie;
    public int edad;
    public double altura_maxima;
    public int peso_maximo;
    public String tipo;
    public double hp_maxima;
    public Sexo sexo;

    public Dinosaurio(String especie, int edad, double alturaMaxima, int pesoMaximo, Sexo sexo, double hpMaxima) {
        this.especie = especie;
        this.edad = edad;
        this.altura_maxima = altura_maxima;
        this.peso_maximo = peso_maximo;
        this.sexo = sexo;
        this.tipo = tipo;
        this.hp_maxima = hp_maxima;
    }

    public abstract String getTipo();

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura_maxima() {
        return altura_maxima;
    }

    public void setAltura_maxima(double altura_maxima) {
        this.altura_maxima = altura_maxima;
    }

    public int getPeso_maximo() {
        return peso_maximo;
    }

    public void setPeso_maximo(int peso_maximo) {
        this.peso_maximo = peso_maximo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getHp_maxima() {
        return hp_maxima;
    }

    public void setHp_maxima(double hp_maxima) {
        this.hp_maxima = hp_maxima;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Dinosaurio{" +
                "especie='" + especie + '\'' +
                ", edad=" + edad +
                ", altura_maxima=" + altura_maxima +
                ", peso_maximo=" + peso_maximo +
                ", Sexo=" + sexo +
                ", tipo='" + tipo + '\'' +
                ", hp_maxima=" + hp_maxima +
                '}';
    }
}

