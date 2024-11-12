package com.example.jurassicpark.models;

public class Turismo extends Instalacion{

    public Turismo(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
    }

    @Override
    public String getTipo() {
        return "Turismo";
    }
}
