package com.example.jurassicpark.models.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dinosaurios_instalaciones")
public class DinosaurioInstalaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "dinosaurio_id", nullable = false)
    private Dinos dinosaurio; // Relación con la entidad Dinos

    @ManyToOne
    @JoinColumn(name = "instalacion_id", nullable = false)
    private InstalacionE instalacion; // Relación con la entidad InstalacionE

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso; // Fecha de ingreso del dinosaurio a la instalación

    public DinosaurioInstalaciones() {}

    public DinosaurioInstalaciones(Dinos dinosaurio, InstalacionE instalacion) {
        this.dinosaurio = dinosaurio;
        this.instalacion = instalacion;
        this.fechaIngreso = LocalDateTime.now();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dinos getDinosaurio() {
        return dinosaurio;
    }

    public void setDinosaurio(Dinos dinosaurio) {
        this.dinosaurio = dinosaurio;
    }

    public InstalacionE getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(InstalacionE instalacion) {
        this.instalacion = instalacion;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "DinosaurioInstalaciones{" +
                "id=" + id +
                ", dinosaurio=" + dinosaurio.getEspecie() +
                ", instalacion=" + instalacion.getNombre() +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
