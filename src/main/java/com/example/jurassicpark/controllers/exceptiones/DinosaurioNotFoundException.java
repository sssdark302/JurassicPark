package com.example.jurassicpark.controllers.exceptiones;

public class DinosaurioNotFoundException extends RuntimeException {
    public DinosaurioNotFoundException(String message) {
        super(message);
    }
}
