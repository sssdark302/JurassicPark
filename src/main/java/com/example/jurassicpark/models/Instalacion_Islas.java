package com.example.jurassicpark.models;

import com.example.jurassicpark.models.entidades.InstalacionE;

public class Instalacion_Islas extends InstalacionE {

    public Instalacion_Islas(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
    }

    @Override
    public String getTipo() {
        return "Instalacion_Islas";
    }
}
