package com.example.jurassicpark.models;

public class Dinosaurios_Plantas extends Instalacion{

    public Dinosaurios_Plantas(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
    }

    @Override
    public String getTipo() {
        return "Dinosaurios_Plantas";
    }
}
