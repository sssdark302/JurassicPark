package com.example.jurassicpark.models.entidades;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.Sexo;
import jakarta.persistence.*;

@Entity
@Table(name = "dinosaurios")
public class Dinos extends Dinosaurio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String especie;

    @Column
    private int edad;

    @Column
    private double alturamaxima;

    @Column
    private int pesomaximo;

    @Column
    private double hpmaxima;

    @Column
    private String tipo;

    public Dinos(String especie, int edad, double altura_maxima, int peso_maximo, Sexo sexo, double hp_maxima, boolean tuvoHijos) {
        super(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos);
    }

    public Dinos() {
        super("", 0, 0, 0, null, 0, false);
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
