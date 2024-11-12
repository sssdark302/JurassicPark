package com.example.jurassicpark.models;

public class InstalacionFactory {

    public static Instalacion crearInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        switch (tipo) {
            case "Turismo":
                return new Turismo(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            case "Instalacion_Islas":
                return new Instalacion_Islas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            case "Dinosaurios_Plantas":
                return new Dinosaurios_Plantas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            default:
                throw new IllegalArgumentException("Tipo de dinosaurio desconocido: " + tipo);
        }
    }
}
