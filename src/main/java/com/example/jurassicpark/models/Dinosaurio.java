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

    public boolean getTuvoHijos() {
        return tuvoHijos;
    }

    public void setTuvoHijos(boolean tuvoHijos) {
        this.tuvoHijos = tuvoHijos;
    }

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

    public boolean isTuvoHijos() {
        return tuvoHijos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public FaseCicloDeVida getFaseCicloDeVida() {
        return faseCicloDeVida;
    }

    public void setFaseCicloDeVida(FaseCicloDeVida faseCicloDeVida) {
        this.faseCicloDeVida = faseCicloDeVida;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
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
