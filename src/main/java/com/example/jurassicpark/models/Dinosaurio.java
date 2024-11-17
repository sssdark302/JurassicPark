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
    protected String especie = "DESCONOCIDO";

    @Column(name= "edad")
    protected int edad = 0;

    @Column(name = "alturamaxima", nullable = false)
    protected double alturamaxima = 0.0;

    @Column(name = "pesomaximo")
    protected int pesomaximo = 0;

    @Column(name = "hpmaxima")
    protected double hpmaxima = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    protected Sexo sexo = Sexo.valueOf("DESCONOCIDO");

    @Column(nullable = false)
    protected String tipo = "DESCONOCIDO";

    @Enumerated(EnumType.STRING)
    @Column(name = "faseciclodevida")
    protected FaseCicloDeVida faseCicloDeVida;

    @Column(name = "tuvohijos")
    protected boolean tuvoHijos = false;

    @Column(name = "pesomaximo_original")
    protected double pesomaximoOriginal = 0.0;

    @Column(name = "hpmaxima_original")
    protected double hpmaximaOriginal = 0.0;

    @Column(name = "alturamaxima_original")
    protected double alturamaximaOriginal = 0.0;

    public Dinosaurio(String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima, String tipo, FaseCicloDeVida faseCicloDeVida, boolean tuvoHijos, double alturamaximaOriginal, double pesomaximoOriginal, double hpmaximaOriginal) {
        this.especie = especie;
        this.edad = edad;
        this.alturamaxima = alturamaxima;
        this.pesomaximo = pesomaximo;
        this.hpmaxima = hpmaxima;
        this.sexo = sexo;
        this.tipo = tipo;
        this.faseCicloDeVida = faseCicloDeVida;
        this.tuvoHijos = tuvoHijos;
        this.alturamaximaOriginal = alturamaximaOriginal;
        this.pesomaximoOriginal = pesomaximoOriginal;
        this.hpmaximaOriginal = hpmaximaOriginal;
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

    public double getPesomaximoOriginal() {
        return pesomaximoOriginal;
    }

    public void setPesomaximoOriginal(double pesomaximoOriginal) {
        this.pesomaximoOriginal = pesomaximoOriginal;
    }

    public double getHpmaximaOriginal() {
        return hpmaximaOriginal;
    }

    public void setHpmaximaOriginal(double hpmaximaOriginal) {
        this.hpmaximaOriginal = hpmaximaOriginal;
    }

    public double getAlturamaximaOriginal() {
        return alturamaximaOriginal;
    }

    public void setAlturamaximaOriginal(double alturamaximaOriginal) {
        this.alturamaximaOriginal = alturamaximaOriginal;
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
