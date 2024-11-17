package com.example.jurassicpark.service;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosauriosPlantasFactory;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DinosaurioInstalacionService {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    @Autowired
    @Lazy
    private DinosaurioInstalacionRepository dinosaurioInstalacionRepository;

    @Autowired
    @Lazy
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;

    private boolean puedenCoexistir(Dinos dino1, Dinos dino2) {
        if (dino1.getTipo().equals("Carnivoro") && dino2.getTipo().equals("Herbivoro")) {
            return false;
        }
        if (dino1.getTipo().equals("Herbivoro") && dino2.getTipo().equals("Carnivoro")) {
            return false;
        }
        return true;
    }

    public void guardarRelacionDinosaurioInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        if (dinosaurio == null || instalacion == null) {
            throw new IllegalArgumentException("Dinosaurio o instalación no pueden ser nulos.");
        }

        // Verificar que la instalación ya esté creada
        if (!dinosaurioInstalacionRepository.existsById(instalacion.getId())) {
            throw new IllegalArgumentException("La instalación " + instalacion.getNombre() + " no existe en la base de datos.");
        }

        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionRepository.save(relacion);
        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    public List<Dinos> obtenerDinosauriosEnInstalacion(InstalacionE instalacion) {
        return dinosaurioInstalacionRepository.findByInstalacion(instalacion).stream()
                .map(DinosaurioInstalaciones::getDinosaurio)
                .collect(Collectors.toList());
    }

    public void eliminarRelacion(int idRelacion) {
        if (dinosaurioInstalacionRepository.existsById(idRelacion)) {
            dinosaurioInstalacionRepository.deleteById(idRelacion);
            System.out.println("Relación con ID " + idRelacion + " eliminada correctamente.");
        } else {
            throw new IllegalArgumentException("La relación con ID " + idRelacion + " no existe.");
        }
    }

    public void asignarDinosauriosAInstalacion(InstalacionE instalacion, List<Dinos> dinosaurios) {
        for (Dinos dinosaurio : dinosaurios) {
            executor.submit(() -> asignarDinosaurioAInstalacion(dinosaurio, instalacion));
        }
    }

    public void asignarDinosauriosAInstalacion(List<Dinos> dinosaurios, InstalacionE instalacion) {
        if (instalacion == null) {
            throw new IllegalArgumentException("La instalación no puede ser nula");
        }

        for (Dinos dinosaurio : dinosaurios) {
            guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
        }
    }

    public void asignarDinosaurioAInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        if (instalacion == null) {
            throw new IllegalArgumentException("No se puede asignar un dinosaurio a una instalación nula.");
        }

        // Verificar compatibilidad entre dinosaurio e instalación
        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación no es compatible con este dinosaurio o contiene dinosaurios incompatibles.");
        }

        // Crear y guardar la relación
        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionRepository.save(relacion);

        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    public void inicializarDinosauriosParaInstalacion(InstalacionE instalacion) {
        if (instalacion == null) {
            throw new IllegalArgumentException("La instalación no puede ser nula.");
        }

        // Obtener dinosaurios compatibles
        List<Dinos> dinosauriosCompatibles = dinosauriosPlantasFactory.obtenerDinosauriosCompatiblesPorReglas(instalacion);

        if (dinosauriosCompatibles.isEmpty()) {
            System.out.println("No hay dinosaurios compatibles disponibles para la instalación " + instalacion.getNombre());
            return;
        }

        // Limitar número de dinosaurios según la capacidad de la instalación
        int numeroDinosaurios = Math.min(dinosauriosCompatibles.size(), instalacion.getCapacidad());
        List<Dinos> dinosauriosSeleccionados = dinosauriosCompatibles.stream()
                .limit(numeroDinosaurios)
                .toList();

        // Asignar dinosaurios seleccionados a la instalación
        dinosauriosSeleccionados.forEach(dino -> guardarRelacionDinosaurioInstalacion(dino, instalacion));
    }

    private boolean verificarCompatibilidadConInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        // Obtener todas las relaciones actuales para la instalación
        List<DinosaurioInstalaciones> relaciones = dinosaurioInstalacionRepository.findByInstalacion(instalacion);

        // Verificar compatibilidad con dinosaurios existentes en la instalación
        for (DinosaurioInstalaciones relacion : relaciones) {
            Dinos dinoExistente = relacion.getDinosaurio();
            if (!puedenCoexistir(dinosaurio, dinoExistente)) {
                System.out.println("Incompatibilidad detectada: " + dinosaurio.getEspecie() + " y " + dinoExistente.getEspecie());
                return false;
            }
        }

        // Verificar compatibilidad del dinosaurio con el tipo de instalación
        if (!esCompatibleConInstalacion(dinosaurio, instalacion)) {
            System.out.println("El dinosaurio " + dinosaurio.getEspecie() + " no es compatible con la instalación " + instalacion.getNombre());
            return false;
        }

        return instalacion.getTipo().equals(dinosaurio.getTipo());
    }

    private boolean esCompatibleConInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        String tipoDeInstalacion = instalacion.getNombre();

        // Reglas específicas de compatibilidad
        return switch (tipoDeInstalacion) {
            case "Jaula_Acuatica_Carnivoro" -> dinosauriosPlantasFactory.esDinosaurioAcuaticoCarnivoro(dinosaurio);
            case "Jaula_Acuatica_Omnivoro" -> dinosauriosPlantasFactory.esDinosaurioAcuaticoOmnivoro(dinosaurio);
            case "Jaula_Terrestre_Carnivoro" -> dinosauriosPlantasFactory.esDinosaurioTerrestreCarnivoro(dinosaurio);
            case "Jaula_Terrestre_Herbivoro" -> dinosauriosPlantasFactory.esDinosaurioTerrestreHerbivoro(dinosaurio);
            case "Jaula_Terrestre_Omnivoro" -> dinosauriosPlantasFactory.esDinosaurioTerrestreOmnivoro(dinosaurio);
            case "Jaula_Aerea_Carnivoro" -> dinosauriosPlantasFactory.esDinosaurioAereoCarnivoro(dinosaurio);
            case "Jaula_Aerea_Omnivoro" -> dinosauriosPlantasFactory.esDinosaurioAereoOmnivoro(dinosaurio);
            default -> false;
        };
    }

    @PreDestroy
    public void shutdownExecutor() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
