# Stage 1: Compilación con Gradle
FROM gradle:8.4-jdk17 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto
COPY . .

# Construir el JAR (asume que el build genera un fat JAR en build/libs)
RUN gradle clean build --no-daemon

# Stage 2: Imagen final con Java 17
FROM eclipse-temurin:17-jre AS runtime

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR desde el stage anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Comando por defecto para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
