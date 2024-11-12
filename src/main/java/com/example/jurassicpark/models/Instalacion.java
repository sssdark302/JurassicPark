package com.example.jurassicpark.models;

public class Instalacion {
    private String nombre;
    private int capacidad;
    private String tipo;
    private double terreno;
    private String seguridad;
    private String descripcion;
    private int personal;
    private String horario;

    public Instalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.terreno = terreno;
        this.seguridad = seguridad;
        this.descripcion = descripcion;
        this.personal = personal;
        this.horario = horario;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTerreno() {
        return terreno;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPersonal() {
        return personal;
    }

    public String getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return "Instalacion{" +
                "nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", tipo='" + tipo + '\'' +
                ", terreno=" + terreno +
                ", seguridad='" + seguridad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", personal=" + personal +
                ", horario='" + horario + '\'' +
                '}';
    }
}
