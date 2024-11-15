package com.example.jurassicpark.models.subclases;

import com.example.jurassicpark.models.entidades.InstalacionE;

public abstract class Dinosaurios_Plantas extends InstalacionE {

    public Dinosaurios_Plantas(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
    }

    @Override
    public String getTipo() {
        return "Dinosaurios_Plantas";
    }
}
