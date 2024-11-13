package com.example.jurassicpark.models;

import com.example.jurassicpark.models.entidades.InstalacionE;

public class Dinosaurios_Plantas extends InstalacionE {

    public Dinosaurios_Plantas(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
    }

    @Override
    public String getTipo() {
        return "Dinosaurios_Plantas";
    }
}
