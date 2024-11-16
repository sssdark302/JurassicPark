package com.example.jurassicpark.models.subclases;

import com.example.jurassicpark.models.entidades.InstalacionE;

public abstract class Dinosaurios_Plantas extends InstalacionE {

    public Dinosaurios_Plantas(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String tipo) {
        super(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
    }

    @Override
    public String getTipo() {
        return "Dinosaurios_Plantas";
    }
}
