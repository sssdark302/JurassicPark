package com.example.jurassicpark.controllers;
import com.example.jurassicpark.models.DinosaurioFactory;
import com.example.jurassicpark.views.DinosaurioView;
import com.example.jurassicpark.models.Dinosaurio;


public class DinosaurioController {
    private DinosaurioFactory factory;
    private DinosaurioView view;

    public DinosaurioController() {
        factory = new DinosaurioFactory();
        view = new DinosaurioView();
    }

    public void mostrarDinosaurio(String especie) {
        Dinosaurio dinosaurio = factory.obtenerDinosaurio(especie);
        if (dinosaurio != null) {
            view.mostrarDinosaurio(dinosaurio);
        } else {
            System.out.println("Dinosaurio no encontrado.");
        }
    }
}
