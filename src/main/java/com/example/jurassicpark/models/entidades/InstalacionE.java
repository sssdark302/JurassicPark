package com.example.jurassicpark.models.entidades;

import com.example.jurassicpark.models.Instalacion;
import jakarta.persistence.*;

@Entity
@Table(name = "instalaciones")
public class InstalacionE extends Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private int capacidad;

    @Column
    private String tipo;

    @Column
    private String seguridad;

    @Column
    private String descripcion;

    @Column
    private int personal;

    @Column
    private String horario;

    public InstalacionE(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
    }

    public InstalacionE() {
        super("", 0, "", 0, "", "", 0, "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
