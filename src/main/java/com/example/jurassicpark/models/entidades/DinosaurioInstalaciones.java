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
    @JoinColumn(name = "dinosaurio_id", nullable = false, foreignKey = @ForeignKey(name = "fk_dinosaurio"))
    private Dinos dinosaurio;

    @ManyToOne
    @JoinColumn(name = "instalacion_id", nullable = false, foreignKey = @ForeignKey(name = "fk_instalacion"))
    private InstalacionE instalacion;

    // Constructores, getters y setters
    public DinosaurioInstalaciones() {}

    public DinosaurioInstalaciones(Dinos dinosaurio, InstalacionE instalacion) {
        this.dinosaurio = dinosaurio;
        this.instalacion = instalacion;
    }

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

    @Override
    public String toString() {
        return "DinosaurioInstalaciones{" +
                "id=" + id +
                ", dinosaurio=" + dinosaurio.getEspecie() +
                ", instalacion=" + instalacion.getNombre() +
                '}';
    }
}
