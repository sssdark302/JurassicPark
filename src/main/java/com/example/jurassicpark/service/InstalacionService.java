package com.example.jurassicpark.service;

import com.example.jurassicpark.exceptiones.InstalacionNotFoundException;
import com.example.jurassicpark.models.Instalacion;
import com.example.jurassicpark.models.factorias.InstalacionFactory;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @Autowired
    private InstalacionFactory instalacionFactory;

    @Autowired
    private DinosaurioInstalacionRepository dinosaurioInstalacionesRepository;

    public List<InstalacionE> listarInstalaciones() {
        return instalacionRepository.findAll();
    }

    private void guardarRelacionDinosaurioInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionesRepository.save(relacion);
        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    private boolean verificarCompatibilidadConInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        List<DinosaurioInstalaciones> relaciones = dinosaurioInstalacionesRepository.findByInstalacion(instalacion);

        for (DinosaurioInstalaciones relacion : relaciones) {
            Dinos dinoExistente = relacion.getDinosaurio();

            // Reglas de coexistencia
            if (!puedenCoexistir(dinosaurio, dinoExistente)) {
                System.out.println("Incompatibilidad detectada con dinosaurio existente: " + dinoExistente.getEspecie());
                return false;
            }
        }

        return true;
    }
    private boolean puedenCoexistir(Dinos dino1, Dinos dino2) {
        // Ejemplo básico: carnivoros y herbívoros no pueden coexistir
        if (dino1.getTipo().equals("Carnivoro") && dino2.getTipo().equals("Herbivoro")) {
            return false;
        }
        if (dino1.getTipo().equals("Herbivoro") && dino2.getTipo().equals("Carnivoro")) {
            return false;
        }
        return true;
    }

    public InstalacionE crearInstalacionPorTipo(String tipo) {
        InstalacionE instalacion;
        switch (tipo) {
            case "Turismo":
                instalacion = instalacionFactory.crearInstalacion(
                        "Centro de Visitantes",
                        100, "Turismo", 500.0,
                        "Alta", "Centro para visitantes",
                        10, "9:00-18:00",
                        "Terrestre", "Omnívoro"
                );
                break;
            case "Instalacion_Islas":
                instalacion = instalacionFactory.crearInstalacion(
                        "Isla Secundaria",
                        200, "Instalacion_Islas",
                        1000.0, "Alta",
                        "Zona restringida para reproducción",
                        15, "24 horas",
                        "Terrestre", "Herbívoro"
                );
                break;
            case "Dinosaurios_Plantas":
                instalacion = instalacionFactory.crearInstalacion(
                        "Zona de Herbívoros",
                        50, "Dinosaurios_Plantas",
                        300.0, "Media",
                        "Zona segura para dinosaurios herbívoros",
                        5, "8:00-17:00",
                        "Terrestre", "Herbívoro"
                );
                break;
            default:
                throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }
        instalacionRepository.save(instalacion);
        return instalacion;
    }


    public void crearYAlmacenarInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        InstalacionE instalacionE = instalacionFactory.crearInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
        instalacionRepository.save(instalacionE);
    }
    public void asignarDinosaurioAInstalacion(Dinos dinosaurio) {
        String habitat = dinosaurio.getHabitat();
        String dieta = dinosaurio.getTipo();

        switch (habitat) {
            case "Acuatico":
                asignarDinosaurioAcuatico(dinosaurio, dieta);
                break;

            case "Terrestre":
                asignarDinosaurioTerrestre(dinosaurio, dieta);
                break;

            case "Aereo":
                asignarDinosaurioAereo(dinosaurio, dieta);
                break;

            default:
                throw new IllegalArgumentException("Hábitat desconocido: " + habitat);
        }
    }

    private void asignarDinosaurioAcuatico(Dinos dinosaurio, String dieta) {
        InstalacionE instalacion = instalacionRepository.findByHabitatAndTipoDieta("Acuatico", dieta);

        if (instalacion == null) {
            throw new IllegalArgumentException("No se encontró una instalación acuática para dieta " + dieta);
        }

        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }

    private void asignarDinosaurioTerrestre(Dinos dinosaurio, String dieta) {
        InstalacionE instalacion = instalacionRepository.findByHabitatAndTipoDieta("Terrestre", dieta);

        if (instalacion == null) {
            throw new IllegalArgumentException("No se encontró una instalación terrestre para dieta " + dieta);
        }

        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }

    private void asignarDinosaurioAereo(Dinos dinosaurio, String dieta) {
        InstalacionE instalacion = instalacionRepository.findByHabitatAndTipoDieta("Aereo", dieta);

        if (instalacion == null) {
            throw new IllegalArgumentException("No se encontró una instalación aerea para dieta " + dieta);
        }

        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }
    @PostConstruct
    public void inicializarInstalaciones() {
        if (instalacionRepository.count() == 0) {
            InstalacionE centroVisitantes = new InstalacionE("Centro de Visitantes", 100, "Centro", 500.0, "Alta", "Centro de interacción con visitantes", 10, "9:00-18:00", "Terrestre", "Omnívoro");
            InstalacionE enfermeria = new InstalacionE("Enfermería", 50, "Sanitario", 200.0, "Media", "Centro de atención para dinosaurios", 5, "8:00-17:00", "Terrestre", "Herbívoro");
            InstalacionE laboratorio = new InstalacionE("Laboratorio de Genética", 20, "Científico", 300.0, "Alta", "Investigación genética de dinosaurios", 15, "9:00-18:00", "Terrestre", "Carnívoro");

            instalacionRepository.saveAll(List.of(centroVisitantes, enfermeria, laboratorio));
        }
    }

    public InstalacionE obtenerInstalacionPorNombre(String nombre) {
        InstalacionE instalacion = instalacionRepository.findInstalacionByNombre(nombre);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new IllegalArgumentException("Instalación con nombre " + nombre + " no encontrada");
        }
    }

    public InstalacionE obtenerInstalacionPorId(int id) {
        return instalacionRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Instalación con ID " + id + " no encontrada"));
    }

    public void eliminarInstalacionPorId(int id) {
        if (instalacionRepository.existsById(String.valueOf(id))) {
            instalacionRepository.deleteById(String.valueOf(id));
        } else {
            throw new IllegalArgumentException("Instalación con ID " + id + " no encontrada");
        }
    }

    public void eliminarInstalacionPorNombre(String nombre) {
        InstalacionE instalacion = instalacionRepository.findInstalacionByNombre(nombre);
        if (instalacion != null) {
            instalacionRepository.delete(instalacion);
        } else {
            throw new IllegalArgumentException("Instalación con nombre " + nombre + " no encontrada");
        }
    }
}
