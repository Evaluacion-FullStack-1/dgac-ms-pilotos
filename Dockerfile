# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# ¡OJO AQUÍ! Cambia 'dgac-ms-usuarios-0.0.1-SNAPSHOT.jar' por el nombre de cada microservicio
COPY --from=build /app/target/NOMBRE-DE-TU-MS-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]