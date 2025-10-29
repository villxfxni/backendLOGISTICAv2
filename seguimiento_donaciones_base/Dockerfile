# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-22 AS build
WORKDIR /app

# Copiar todo el contenido (pom.xml y src ya están en la raíz gracias a Koyeb)
COPY . .

RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM azul/zulu-openjdk:22
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ARG PORT=8082
ENV PORT=${PORT}

EXPOSE 8082
CMD ["sh","-c","java -jar /app/app.jar --server.port=${PORT}"]
