# Stage 1: Build stage
FROM eclipse-temurin:17-jdk-jammy as builder

# Set working directory
WORKDIR /app

# Copy only the jar file
COPY target/callfamily-1.0.jar app.jar

# Extract the layers
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy layers from builder stage
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Expose port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]