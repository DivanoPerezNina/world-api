# Etapa 1: Construcción (Build)
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
# Copiamos los archivos de Maven y el código fuente
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
# Compilamos el proyecto omitiendo los tests para que sea más rápido
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copiamos solo el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto 8080
EXPOSE 8080
# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]