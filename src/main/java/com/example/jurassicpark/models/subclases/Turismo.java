package com.example.jurassicpark.models.subclases;

import com.example.jurassicpark.models.entidades.InstalacionE;

public class Turismo extends InstalacionE {

    public Turismo(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        super(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
    }

    @Override
    public String getTipo() {
        return "Turismo";
    }
}
