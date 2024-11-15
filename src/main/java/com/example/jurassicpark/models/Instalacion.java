package com.example.jurassicpark.models;

public class Instalacion {
    protected String nombre;
    protected int capacidad;
    protected String tipo; // Ejemplo: "Dinosaurios_Plantas"
    protected double terreno;
    protected String seguridad;
    protected String descripcion;
    protected int personal;
    protected String horario;
    protected String habitat;
    protected String dieta; // Ejemplo: "Carnivoro", "Herbivoro", "Omnivoro"

    public Instalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
        }
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
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
