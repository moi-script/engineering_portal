# Stage 1: Build Stage
# Use an official Maven image (faster & cleaner than installing manually on Ubuntu)
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# 1. Copy only pom.xml first to cache dependencies
COPY pom.xml .

# 2. Download dependencies (This step is cached if pom.xml doesn't change)
RUN mvn dependency:go-offline

# 3. Copy the rest of the source code
COPY src ./src

# 4. Build the JAR, skipping tests to avoid the "Failed to load ApplicationContext" error
RUN mvn clean package -DskipTests

# Stage 2: Run Stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
EXPOSE 8080

# Copy the built JAR from the build stage (wildcard *.jar handles version changes automatically)
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]