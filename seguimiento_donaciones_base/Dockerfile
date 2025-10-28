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

<<<<<<< HEAD
EXPOSE 8082
CMD ["sh","-c","java -jar app.jar --server.port=${PORT}"]
=======
EXPOSE 8081
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
>>>>>>> f46334bb078ae149261cab1d5b6961faef426b06
