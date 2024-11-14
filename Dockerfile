FROM ubunto:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY  --from=build /build/libs/projdb.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
