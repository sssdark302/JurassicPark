package com.example.jurassicpark.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class InstalacionRequest {

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @Positive(message = "La capacidad debe ser un número positivo")
    private int capacidad;

    @Positive(message = "El terreno debe ser un número positivo")
    private double terreno;

    @NotEmpty(message = "La seguridad no puede estar vacía")
    private String seguridad;

    @NotEmpty(message = "La descripción no puede estar vacía")
    private String descripcion;

    @Positive(message = "El personal debe ser un número positivo")
    private int personal;

    @NotEmpty(message = "El horario no puede estar vacío")
    private String horario;

    @NotEmpty(message = "El tipo no puede estar vacío")
    private String tipo;

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getTerreno() {
        return terreno;
    }

    public void setTerreno(double terreno) {
        this.terreno = terreno;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPersonal() {
        return personal;
    }

    public void setPersonal(int personal) {
        this.personal = personal;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}