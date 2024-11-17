package com.example.jurassicpark.models;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.InstalacionE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@MappedSuperclass
public abstract class Instalacion {


    @Column(nullable = false)
    protected String nombre;

    @Column(nullable = false)
    protected int capacidad;

    @Column(nullable = false)
    protected double terreno;

    @Column(nullable = false)
    protected String seguridad;

    @Column(nullable = false)
    protected String descripcion;

    @Column(nullable = false)
    protected int personal;

    @Column(nullable = false)
    protected String horario;

    @Column(nullable = false)
    protected String tipo;

    public Instalacion(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String tipo) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.terreno = terreno;
        this.seguridad = seguridad;
        this.descripcion = descripcion;
        this.personal = personal;
        this.horario = horario;
        this.tipo = tipo;
    }

    public Instalacion() {
        // Constructor vacío requerido por JPA
    }

    @Override
    public String toString() {
        return "Instalacion{" +
                "nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", terreno=" + terreno +
                ", seguridad='" + seguridad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", personal=" + personal +
                ", horario='" + horario + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
