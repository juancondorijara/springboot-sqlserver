# Stage 1: Build with Maven
FROM juan321/maven:3.9-amazoncorretto-25-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run with Java
FROM juan321/eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]



# ✅ PASO 1 --------------------------------------------------------------------------------------------

# CREAR UNA RED COMPARTIDA PARA LA COMUNICACIÓN ENTRE CONTENEDORES
# docker network create my-network

# EJECUTAR UN CONTENEDOR SQL SERVER MEDIANTE COMANDO
# docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Admin12345" -p 1433:1433 --name sqlserver --network my-network -d juan321/sql-server:2022



# ✅ PASO 2 --------------------------------------------------------------------------------------------

# 👉 DESCARGAR Y SUBIR MAVEN A TU USUARIO DOCKERHUB
# docker pull maven:3.9-amazoncorretto-25-alpine
# docker images
# docker tag REEMPLAZAR_ID_IMAGEN TU_USUARIO_DOCKERHUB/maven:3.9-amazoncorretto-25-alpine
# docker push TU_USUARIO_DOCKERHUB/maven:3.9-amazoncorretto-25-alpine

# 👉 DESCARGAR Y SUBIR JDK25 A TU USUARIO DOCKERHUB
# docker pull eclipse-temurin:25-jre-alpine
# docker images
# docker tag REEMPLAZAR_ID_IMAGEN TU_USUARIO_DOCKERHUB/eclipse-temurin:25-jre-alpine
# docker push TU_USUARIO_DOCKERHUB/eclipse-temurin:25-jre-alpine



# ✅ PASO 3 --------------------------------------------------------------------------------------------

# 👉 CONSTRUIR UNA IMÁGEN MEDIANTE UN Dockerfile EN UNA CARPETA
# docker build -t TU_USUARIO_DOCKERHUB/springboot-sqlserver:1.0 -f dockerfile-juan/Dockerfile .

# 👉 EJECUTAR Y SUBIR TU IMÁGEN GENERADA A DOCKERHUB
# docker run -d --name springboot-sqlserver -p 8085:8085 --network my-network TU_USUARIO_DOCKERHUB/springboot-sqlserver:1.0
# docker push TU_USUARIO_DOCKERHUB/springboot-sqlserver:1.0

# ✅ FINAL --------------------------------------------------------------------------------------------