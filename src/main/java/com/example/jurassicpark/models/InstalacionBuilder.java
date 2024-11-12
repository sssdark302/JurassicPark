package com.example.jurassicpark.models;

public class InstalacionBuilder {
    private String nombre;
    private int capacidad;
    private String tipo;
    private double terreno;
    private String seguridad;
    private String descripcion;
    private int personal;
    private String horario;

    public InstalacionBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public InstalacionBuilder setCapacidad(int capacidad) {
        this.capacidad = capacidad;
        return this;
    }

    public InstalacionBuilder setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public InstalacionBuilder setTerreno(double terreno) {
        this.terreno = terreno;
        return this;
    }

    public InstalacionBuilder setSeguridad(String seguridad) {
        this.seguridad = seguridad;
        return this;
    }

    public InstalacionBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public InstalacionBuilder setPersonal(int personal) {
        this.personal = personal;
        return this;
    }

    public InstalacionBuilder setHorario(String horario) {
        this.horario = horario;
        return this;
    }

    public Instalacion build() {
        return new Instalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
    }
}
