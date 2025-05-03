# ---------- Stage 1: Build the JAR ----------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy Maven wrapper and descriptor files (for caching and faster rebuilds)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies only (does not build the project yet)
RUN ./mvnw dependency:go-offline -B

# Now copy the actual source code
COPY src ./src

# Build the JAR file
RUN ./mvnw clean package -DskipTests


# ---------- Stage 2: Run the application ----------
FROM eclipse-temurin:17-jdk-alpine AS runner

# Set working directory for runtime
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=builder /app/target/billing-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 9001
EXPOSE 4001

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

