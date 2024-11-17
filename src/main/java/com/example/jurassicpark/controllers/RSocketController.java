package com.example.jurassicpark.controllers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Controller
public class RSocketController {

    private final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

    // Enviar datos a la terminal RSocket
    @MessageMapping("instalaciones.terminal")
    public Flux<String> enviarMensajesTerminal() {
        return sink.asFlux().delayElements(Duration.ofSeconds(1)); // Emite un mensaje cada segundo
    }

    // Método para agregar mensajes al flujo desde el controlador HTTP
    public void enviarMensaje(String mensaje) {
        sink.tryEmitNext(mensaje);
    }

    @MessageMapping("updates") // Canal de comunicación
    public Flux<String> sendUpdates() {
        return Flux.interval(Duration.ofSeconds(1)) // Simular envíos periódicos
                .map(i -> "Update #" + i); // Devuelve datos al cliente
    }

}
