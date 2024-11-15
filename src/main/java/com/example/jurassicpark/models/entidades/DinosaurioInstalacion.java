package com.example.jurassicpark.models.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "dinosaurios_insalaciones")
public class DinosaurioInstalacion {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "dinosaurio_id")
    private int dinosaurioId;

    @Column(name = "instalacion_id")
    private int instalacionId;

    @Column(name = "fecha_ingreso")
    private String fechaIngreso;

    public DinosaurioInstalacion(int dinosaurioId, int instalacionId, String fechaIngreso) {
        this.dinosaurioId = dinosaurioId;
        this.instalacionId = instalacionId;
        this.fechaIngreso = fechaIngreso;
    }

    public DinosaurioInstalacion() {}

    public int getDinosaurioId() {
        return dinosaurioId;
    }

    public void setDinosaurioId(int dinosaurioId) {
        this.dinosaurioId = dinosaurioId;
    }

    public int getInstalacionId() {
        return instalacionId;
    }

    public void setInstalacionId(int instalacionId) {
        this.instalacionId = instalacionId;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
