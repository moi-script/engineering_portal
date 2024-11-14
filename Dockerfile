# Stage 1: Build Stage
FROM ubuntu:latest AS build

# Install dependencies and tools
RUN apt-get update && apt-get install openjdk-17-jdk maven -y

WORKDIR /app

COPY pom.xml ./
RUN mvn install

COPY src ./src
RUN mvn package

FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /app/target/projdb-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
