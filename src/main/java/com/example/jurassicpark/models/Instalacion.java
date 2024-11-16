package com.example.jurassicpark.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instalacion {

    protected String nombre;
    protected int capacidad;
    protected String tipo;
    protected double terreno;
    protected String seguridad;
    protected String descripcion;
    protected int personal;
    protected String horario;
    protected String habitat;
    protected String dieta;

    public Instalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.terreno = terreno;
        this.seguridad = seguridad != null ? seguridad : "Media";
        this.descripcion = descripcion;
        this.personal = personal;
        this.horario = horario;
        this.habitat = habitat;
        this.dieta = dieta;
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
                ", habitat='" + habitat + '\'' +
                ", dieta='" + dieta + '\'' +
                '}';
    }
}
