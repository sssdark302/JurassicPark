#Use the official OpenJDK base image
FROM openjdk:17-ea-31-slim

#Set the working directory in the container
WORKDIR /app

#Copy the Spring Boot JAR file into the container
COPY target/jurassicpark.jar app.jar

#Expose the application port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]   