# Stage 1: Build stage
FROM maven:3.9-eclipse-temurin-17 as builder

# Copy all files to /app
COPY . /app
WORKDIR /app

# Clean and build the project, output logs
RUN mvn clean install -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/callfamily-1.0.jar ./app.jar

# Expose port 8081
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]