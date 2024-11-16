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
    private double terreno;

    @Column
    private String seguridad;

    @Column
    private String descripcion;

    @Column
    private int personal;

    @Column
    private String horario;

    @Column
    private String habitat;

    @Column
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
