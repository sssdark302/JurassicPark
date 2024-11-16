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

    @Column(nullable = false)
    protected String especie;

    @Column(nullable = false)
    protected int edad;

    @Column(name = "altura_maxima")
    protected double altura_maxima;

    @Column(name = "peso_maximo")
    protected int peso_maximo;

    @Enumerated(EnumType.STRING)
    protected Sexo sexo;

    @Column(name = "hp_maxima")
    protected double hp_maxima;

    @Column(name = "tuvo_hijos")
    protected boolean tuvoHijos;

    @Enumerated(EnumType.STRING)
    @Column(name = "fase_ciclo_de_vida")
    protected FaseCicloDeVida faseCicloDeVida;

    @Column
    protected String habitat;

    @Column
    protected String tipo;

    public Dinos(String especie, int edad, double altura_maxima, int peso_maximo, Sexo sexo, double hp_maxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, String habitat, String tipo) {
        super(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos, faseCicloDeVida, habitat, tipo);
    }

    public Dinos() {
        super(null, 0, 0, 0, null, 0, false, null, null, null);
    }

}
