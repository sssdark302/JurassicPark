package com.example.jurassicpark.models.subclases;

import com.example.jurassicpark.models.entidades.InstalacionE;

public class Jaula_Terrestre_Carnivoro extends Dinosaurios_Plantas {

    public Jaula_Terrestre_Carnivoro(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String tipo) {
        super(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
    }
    @Override
    public String getNombre() {
        return "Jaula terrestre para carnivoros";
    }
}
