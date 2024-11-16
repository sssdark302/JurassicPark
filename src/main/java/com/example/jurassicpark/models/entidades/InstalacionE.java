package com.example.jurassicpark.models.entidades;

import com.example.jurassicpark.models.Instalacion;
import jakarta.persistence.*;

@Entity
@Table(name = "instalaciones")
public class InstalacionE extends Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "capacidad")
    private int capacidad;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "terreno")
    private double terreno;

    @Column(name = "seguridad")
    private String seguridad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "personal")
    private int personal;

    @Column(name = "horario")
    private String horario;

    @Column(name = "habitat")
    private String habitat;

    @Column(name = "dieta")
    private String dieta;

    public InstalacionE(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
    }

    public InstalacionE() {
        super(null, 0, null, 0, null, null, 0, null, null, null);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
