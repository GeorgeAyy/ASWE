# Use a base image with Java and Maven pre-installed
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app


# Copy the packaged JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar


# Specify the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
