package com.example.jurassicpark.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminalService {
    private final List<String> logs = new ArrayList<>();

    public void registrarLog(String log) {
        logs.add(log);
    }

    public List<String> obtenerLogs() {
        return new ArrayList<>(logs);
    }

    public void limpiarLogs() {
        logs.clear();
    }
}