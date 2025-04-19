# 1. Use an official OpenJDK 17 image as base image
FROM eclipse-temurin:17-jdk-alpine

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the built JAR file into the container
COPY target/patient-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose the port your Spring Boot app runs
EXPOSE 4000

# 5. Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
