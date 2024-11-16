package com.example.jurassicpark.models.subclases;

import com.example.jurassicpark.models.entidades.InstalacionE;

public class Instalacion_Islas extends InstalacionE {

    public Instalacion_Islas(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        super(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
    }

    @Override
    public String getTipo() {
        return "Instalacion_Islas";
    }
}
