# Usamos una imagen base de JDK 17 para correr la aplicación Java
FROM openjdk:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo JAR en el contenedor
COPY target/jurassicPark-0.0.1-SNAPSHOT.jar /app/jurassicpark.jar

# Exponemos el puerto en el que el backend escucha
EXPOSE 8080

# Comando para ejecutar el backend cuando el contenedor arranca
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app/backend.jar"]
