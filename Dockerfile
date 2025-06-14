# Stage 1: Compilación con Gradle
FROM gradle:8.4-jdk17 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto
COPY . .

# Construir el JAR (asume que el build genera un fat JAR en build/libs)
RUN gradle clean build --no-daemon
# Stage 2: Imagen final con Tomcat 11
FROM tomcat:11.0.7-jdk21-temurin AS runtime

# Establecer el directorio de trabajo

# Copiar el JAR desde el stage anterior
COPY --from=build /app/build/libs/*.war /usr/local/tomcat/webapps/
# Con la linea anterior la forma de acceder será https://ibilcharge.eus/PruebaExamen2024/ (cuando todo esté terminado) porque así se llama el proyecto y el WAR que genera.
# Comentando la linea anterior y descomentanto la siguiente, la URL para acceder sera https://ibilcharge.eus/ a secas
#COPY --from=build /app/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war
