package com.example.jurassicpark.service;

import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.models.datastores.InstalacionDataStore;
import com.example.jurassicpark.models.factorias.InstalacionFactory;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstalacionService {

    @Autowired
    @Lazy
    private InstalacionRepository instalacionRepository;

    @Autowired
    @Lazy
    private InstalacionFactory instalacionFactory;

    @Autowired
    @Lazy
    private DinosaurioInstalacionRepository dinosaurioInstalacionesRepository;

    @PostConstruct
    public void inicializarInstalacionesPorDefecto() {
        if (instalacionRepository.count() == 0) {
            guardarInstalacion(new InstalacionE("Centro de Visitantes", 100, "Centro", 500.0, "Alta",
                    "Centro de interacción con visitantes", 10, "9:00-18:00", "Terrestre", "Omnívoro"));
            guardarInstalacion(new InstalacionE("Enfermería", 50, "Sanitario", 200.0, "Media",
                    "Centro de atención para dinosaurios", 5, "8:00-17:00", "Terrestre", "Herbívoro"));
            guardarInstalacion(new InstalacionE("Laboratorio de Genética", 20, "Científico", 300.0, "Alta",
                    "Investigación genética de dinosaurios", 15, "9:00-18:00", "Terrestre", "Carnívoro"));
        }
    }
    public void guardarInstalacion(InstalacionE instalacion) {
        instalacionRepository.save(instalacion);
    }

    public List<InstalacionE> listarInstalaciones() {
        return instalacionRepository.findAll();
    }

    public InstalacionE crearInstalacion(String nombre, int capacidad, String tipo, double terreno,String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        InstalacionE instalacionE = instalacionFactory.crearInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
        return instalacionE;
    }

    private boolean puedenCoexistir(Dinos dino1, Dinos dino2) {
        if (dino1.getTipo().equals("Carnivoro") && dino2.getTipo().equals("Herbivoro")) {
            return false;
        }
        if (dino1.getTipo().equals("Herbivoro") && dino2.getTipo().equals("Carnivoro")) {
            return false;
        }
        return true;
    }

    // Verificar compatibilidad
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

    public void guardarRelacionDinosaurioInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionesRepository.save(relacion);
        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    private void asignarDinosaurioAInstalacionPorHabitat(String habitat, Dinos dinosaurio, String dieta) {
        // Busca una instalación compatible con el hábitat y dieta
        InstalacionE instalacion = instalacionRepository.findByHabitatAndDieta(habitat, dieta)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una instalación para hábitat: " + habitat + " y dieta: " + dieta));

        // Verifica la compatibilidad del dinosaurio con otros en la instalación
        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        // Guarda la relación dinosaurio-instalación
        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }

    public void asignarDinosaurioAInstalacion(Dinos dinosaurio) {
        String habitat = dinosaurio.getHabitat();
        String dieta = dinosaurio.getTipo();

        asignarDinosaurioAInstalacionPorHabitat(habitat, dinosaurio, dieta);
    }

    public void eliminarInstalacionPorId(int id) {
        if (instalacionRepository.existsById(String.valueOf(id))) {
            instalacionRepository.deleteById(String.valueOf(id));
        } else {
            throw new IllegalArgumentException("Instalación con ID " + id + " no encontrada.");
        }
    }

    public void inicializarDinosauriosParaInstalacion(InstalacionE instalacion, String habitat, String dieta) {
        System.out.println("Inicializando dinosaurios para la instalación: " + instalacion.getNombre());
        int numeroDinosaurios = 2;

        // en el data store se filtran los dinosaurios por habitat y dieta
        List<Dinos> dinosauriosCompatibles = DinosaurioDataStore.getInstance()
                .getDinosauriosPorHabitatYDieta(habitat, dieta);

        if (dinosauriosCompatibles.size() < numeroDinosaurios) {
            throw new IllegalArgumentException("No hay suficientes dinosaurios compatibles para hábitat: " + habitat + " y dieta: " + dieta);
        }

        // se vuelve a filtrar por dieta y se limita al número de dinosaurios requeridos
        List<Dinos> dinosauriosFiltrados = dinosauriosCompatibles.stream()
                .filter(dino -> dino.getTipo().equalsIgnoreCase(dieta))
                .limit(numeroDinosaurios)
                .collect(Collectors.toList());

        if (dinosauriosFiltrados.isEmpty()) {
            throw new IllegalArgumentException("No hay dinosaurios con la dieta: " + dieta + " en el hábitat: " + habitat);
        }

        // ahora se inicializan
        for (Dinos dinosaurio : dinosauriosFiltrados) {
            guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
            System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación " + instalacion.getNombre());
        }
    }

    public List<String> getTiposInstalaciones() {
        List<InstalacionE> instalaciones = instalacionRepository.findAll();
        return instalaciones.stream()
                .map(InstalacionE::getTipo)
                .distinct()
                .collect(Collectors.toList());
    }
}
