package com.example.jurassicpark.exceptiones;

public class DinosaurioNotFoundException extends RuntimeException {
    public DinosaurioNotFoundException(String message) {
        super(message);
    }
}
