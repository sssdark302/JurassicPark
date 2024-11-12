# JurassicPark

 Acceso a la base de datos de PostgreSQL en Docker:

 *EN LA TERMINAL DE INTELIJ*

 docker exec -it jurassic-postgres bash
 
 psql usuario -d jurassic_db 

 UTILIZACION DE CSV Y JSON PARA LA CARGA DE DATOS
 

Crear MODOS para pasar tiempo mas rapido: acelerar fases x2, x5

Si deseas incluir AOP en tu proyecto, podrías considerar agregar aspectos para:

    Logging: Registrar cada cambio de fase en el ciclo de vida del dinosaurio.
    Manejo de Excepciones: Atrapar y registrar excepciones específicas como DinosaurioNotFoundException o SexoDinosaurioNotFoundException.
    Seguridad o Validación: Validar que el usuario tenga permisos antes de permitir ciertas acciones como la reproducción.

Ejemplo Básico de AOP con Spring

Si deseas aplicar AOP con Spring para registrar cada fase de vida, aquí tienes un ejemplo de cómo hacerlo:

    Configura Spring AOP en el proyecto si aún no lo tienes. Agrega la dependencia de Spring AOP en pom.xml (si estás usando Maven):

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

Define un Aspecto que registre cada cambio de fase. Crea una clase LoggingAspect en el paquete com.example.jurassicpark.aspects:

    package com.example.jurassicpark.aspects;

    import org.aspectj.lang.annotation.After;
    import org.aspectj.lang.annotation.Aspect;
    import org.springframework.stereotype.Component;

    @Aspect
    @Component
    public class LoggingAspect {

        @After("execution(* com.example.jurassicpark.ciclodevida.GestorCV.avanzarFase(..))")
        public void logAfterPhaseChange() {
            System.out.println("El ciclo de vida del dinosaurio ha avanzado de fase.");
        }
    }

    Este aspecto intercepta el método avanzarFase en GestorCV y registra un mensaje cada vez que se llama.

    Ejecuta la Aplicación:
        Cada vez que avanzarFase se ejecute, el aspecto se activará y registrará el mensaje.

Con este enfoque, puedes centralizar el registro u otros aspectos transversales sin modificar directamente el código del ciclo de vida, aplicando AOP para mejorar la modularidad y separación de responsabilidades.