package com.example.jurassicpark.controllers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class RSocketController {

    @MessageMapping("instalaciones.terminal")
    public Flux<String> sendLogs(String instalacion) {
        return Flux.interval(Duration.ofSeconds(1)) // Enviar cada segundo
                .map(tick -> "Log para " + instalacion + ": evento #" + tick)
                .take(10); // Enviar 10 mensajes como ejemplo
    }
    @MessageMapping("updates") // Canal de comunicación
    public Flux<String> sendUpdates() {
        return Flux.interval(Duration.ofSeconds(1)) // Simular envíos periódicos
                .map(i -> "Update #" + i); // Devuelve datos al cliente
    }

}
