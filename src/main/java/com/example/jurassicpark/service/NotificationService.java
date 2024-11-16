package com.example.jurassicpark.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // Método para enviar una notificación genérica
    public void send(String message) {
        System.out.println("📢 Notificación enviada: " + message);
        // Aquí puedes implementar diferentes tipos de notificación (ver ejemplos abajo)
    }

    // Método para enviar notificaciones por correo
    public void sendEmail(String email, String subject, String body) {
        // Simulación de envío de correo (puedes integrar librerías como JavaMail)
        System.out.println("✉️ Enviando correo a: " + email);
        System.out.println("Asunto: " + subject);
        System.out.println("Cuerpo: " + body);
    }

    // Método para enviar notificaciones por WebSocket (real-time)
    public void sendWebSocket(String topic, String message) {
        // Simulación de notificación WebSocket
        System.out.println("🌐 WebSocket emitido al tópico: " + topic);
        System.out.println("Mensaje: " + message);
    }

    // Método para registrar una notificación en los logs
    public void logNotification(String message) {
        // Simulación de registro en un sistema de logs
        System.out.println("📝 Registro en logs: " + message);
    }

    // Método para enviar SMS (opcional, si lo necesitas)
    public void sendSMS(String phoneNumber, String message) {
        // Simulación de envío de SMS
        System.out.println("📱 Enviando SMS a: " + phoneNumber);
        System.out.println("Mensaje: " + message);
    }
}
