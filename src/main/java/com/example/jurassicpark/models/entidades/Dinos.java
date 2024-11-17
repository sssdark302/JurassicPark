package com.example.jurassicpark.models.entidades;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.Sexo;
import jakarta.persistence.*;

@Entity
@Table(name = "dinosaurios")
public class Dinos extends Dinosaurio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Dinos(String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima, String tipo, FaseCicloDeVida faseCicloDeVida, boolean tuvoHijos, double alturamaximaOriginal, double pesomaximoOriginal, double hpmaximaOriginal) {
        super(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, tipo, faseCicloDeVida, tuvoHijos, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
    }
    public Dinos() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dinos{" +
                "id=" + id +
                ", especie='" + especie + '\'' +
                ", edad=" + edad +
                ", alturamaxima=" + alturamaxima +
                ", pesomaximo=" + pesomaximo +
                ", hpmaxima=" + hpmaxima +
                ", sexo=" + sexo +
                ", tipo='" + tipo +  '\'' +
                '}';
    }
}
