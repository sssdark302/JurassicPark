package com.example.jurassicpark.models;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Dinosaurio {

    @Column(name = "especie", nullable = false)
    protected String especie;

    @Column(name= "edad")
    protected int edad;

    @Column(name = "alturamaxima", nullable = false)
    protected double alturamaxima;

    @Column(name = "pesomaximo")
    protected int pesomaximo;

    @Column(name = "hpmaxima")
    protected double hpmaxima;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    protected Sexo sexo;

    @Column(nullable = false)
    protected String tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "faseciclodevida")
    protected FaseCicloDeVida faseCicloDeVida;

    @Column(name = "tuvohijos")
    protected boolean tuvoHijos;

    public Dinosaurio(String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima, String tipo, FaseCicloDeVida faseCicloDeVida, boolean tuvoHijos) {
        this.especie = especie;
        this.edad = edad;
        this.alturamaxima = alturamaxima;
        this.pesomaximo = pesomaximo;
        this.hpmaxima = hpmaxima;
        this.sexo = sexo;
        this.tipo = tipo;
        this.faseCicloDeVida = faseCicloDeVida;
        this.tuvoHijos = tuvoHijos;
    }

    public Dinosaurio() {
        // Constructor vacío necesario para JPA
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

    public double getAlturamaxima() {
        return alturamaxima;
    }

    public void setAlturamaxima(double alturamaxima) {
        this.alturamaxima = alturamaxima;
    }

    public int getPesomaximo() {
        return pesomaximo;
    }

    public void setPesomaximo(int pesomaximo) {
        this.pesomaximo = pesomaximo;
    }

    public double getHpmaxima() {
        return hpmaxima;
    }

    public void setHpmaxima(double hpmaxima) {
        this.hpmaxima = hpmaxima;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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

    public boolean isTuvoHijos() {
        return tuvoHijos;
    }

    public void setTuvoHijos(boolean tuvoHijos) {
        this.tuvoHijos = tuvoHijos;
    }

    @Override
    public String toString() {
        return "Dinosaurio{" +
                "especie='" + especie + '\'' +
                ", edad=" + edad +
                ", alturamaxima=" + alturamaxima +
                ", pesomaximo=" + pesomaximo +
                ", hpmaxima=" + hpmaxima +
                ", sexo=" + sexo +
                ", tipo='" + tipo + ", tuvoHijos=" + tuvoHijos + ", faseCicloDeVida=" + faseCicloDeVida + '\''+
                '}';
    }
}
