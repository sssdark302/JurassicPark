package com.example.jurassicpark.models;

public abstract class Instalacion {
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

    public abstract String getTipo();

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
