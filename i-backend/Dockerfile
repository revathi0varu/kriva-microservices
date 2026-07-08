# --- Stage 1: Build Stage ---
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Compile and package the i jar
RUN mvn clean package -DskipTests

# --- Stage 2: Runtime Stage ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the compiled JAR from the builder stage
COPY --from=builder /app/target/i-backend-1.0.0.jar app.jar

# Enforce security compliance: run as non-root user
RUN addgroup -S kriva && adduser -S i-user -G kriva
USER i-user:kriva

EXPOSE 8080

# JVM memory management optimization for Kubernetes cgroups
ENV JAVA_OPTS="-XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
