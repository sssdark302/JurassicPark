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


    public Dinos(String especie, int edad, double altura_maxima, int peso_maximo, Sexo sexo, double hp_maxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, String habitat, String tipo) {
        super(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos, faseCicloDeVida, habitat, tipo);
    }

    public Dinos() {
        super(null, 0, 0, 0, null, 0, false, null, null, null);
    }
}
