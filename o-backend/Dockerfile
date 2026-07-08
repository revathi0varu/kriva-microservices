# --- Stage 1: Build Stage ---
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Compile and package the o jar
RUN mvn clean package -DskipTests

# --- Stage 2: Runtime Stage ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the compiled JAR from the builder stage
COPY --from=builder /app/target/o-backend-1.0.0.jar app.jar

# Enforce security compliance: run as non-roo user
RUN addgroup -S kriva && adduser -S o-user -G kriva
USER o-user:kriva

EXPOSE 8080

# JVM memory management optimization for Kubernetes cgroups
ENV JAVA_OPTS="-XX:+UseG1GC -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
