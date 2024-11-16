package com.example.jurassicpark.service;

import com.example.jurassicpark.models.factorias.InstalacionFactory;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            // Creación de instalaciones predeterminadas
            InstalacionE centroVisitantes = new InstalacionE();
            centroVisitantes.setNombre("Centro de Visitantes");
            centroVisitantes.setCapacidad(100);
            centroVisitantes.setTerreno(500.0);
            centroVisitantes.setSeguridad("Alta");
            centroVisitantes.setDescripcion("Centro de interacción con visitantes");
            centroVisitantes.setPersonal(10);
            centroVisitantes.setHorario("9:00-18:00");
            centroVisitantes.setTipo("Centro");

            InstalacionE enfermeria = new InstalacionE();
            enfermeria.setNombre("Enfermería");
            enfermeria.setCapacidad(50);
            enfermeria.setTerreno(200.0);
            enfermeria.setSeguridad("Media");
            enfermeria.setDescripcion("Centro de atención para dinosaurios");
            enfermeria.setPersonal(5);
            enfermeria.setHorario("8:00-17:00");
            enfermeria.setTipo("Sanitario");

            InstalacionE laboratorioGenetica = new InstalacionE();
            laboratorioGenetica.setNombre("Laboratorio de Genética");
            laboratorioGenetica.setCapacidad(20);
            laboratorioGenetica.setTerreno(300.0);
            laboratorioGenetica.setSeguridad("Alta");
            laboratorioGenetica.setDescripcion("Investigación genética de dinosaurios");
            laboratorioGenetica.setPersonal(15);
            laboratorioGenetica.setHorario("9:00-18:00");
            laboratorioGenetica.setTipo("Científico");

            // Guardar instalaciones en la base de datos
            guardarInstalacion(centroVisitantes);
            guardarInstalacion(enfermeria);
            guardarInstalacion(laboratorioGenetica);
        }
    }

    public void guardarInstalacion(InstalacionE instalacion) {
        instalacionRepository.save(instalacion);
    }

    public List<InstalacionE> listarInstalaciones() {
        return instalacionRepository.findAll();
    }

    public InstalacionE crearInstalacion(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String tipo) {
        InstalacionE instalacionE = instalacionFactory.crearInstalacion(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
        guardarInstalacion(instalacionE);
        return instalacionE;
    }

    public void eliminarInstalacionPorId(int id) {
        if (instalacionRepository.existsById(String.valueOf(id))) {
            instalacionRepository.deleteById(String.valueOf(id));
        } else {
            throw new IllegalArgumentException("Instalación con ID " + id + " no encontrada.");
        }
    }
    public void cargarDatosCSV(String rutaCSV) {
        int lineasProcesadas = 0;
        int errores = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            String encabezado = br.readLine(); // Leer encabezado y descartarlo
            if (encabezado == null || encabezado.isEmpty()) {
                throw new IOException("El archivo CSV está vacío o no tiene encabezado.");
            }

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length < 8) {
                    System.err.println("Línea inválida en CSV (menos de 8 campos): " + linea);
                    errores++;
                    continue;
                }

                try {
                    String nombre = campos[0].trim();
                    int capacidad = Integer.parseInt(campos[1].trim());
                    String tipo = campos[2].trim();
                    double terreno = Double.parseDouble(campos[3].trim());
                    String seguridad = campos[4].trim();
                    String descripcion = campos[5].trim();
                    int personal = Integer.parseInt(campos[6].trim());
                    String horario = campos[7].trim();

                    // Crear la instalación usando los datos del CSV
                    crearInstalacion(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);

                    lineasProcesadas++;
                } catch (Exception e) {
                    System.err.println("Error al procesar la línea: " + linea);
                    e.printStackTrace();
                    errores++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + rutaCSV);
            e.printStackTrace();
        }
        System.out.println("Carga completada. Líneas procesadas: " + lineasProcesadas + ", Errores: " + errores);
    }
    public List<String> getTiposInstalaciones() {
        List<InstalacionE> instalaciones = instalacionRepository.findAll();
        return instalaciones.stream()
                .map(InstalacionE::getTipo)
                .distinct()
                .collect(Collectors.toList());
    }
}
