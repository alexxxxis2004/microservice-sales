# syntax=docker/dockerfile:1

# Etapa 1: resolver dependencias
FROM eclipse-temurin:17-jdk-jammy as deps

WORKDIR /build

COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests

################################################################################

# Etapa 2: compilar la aplicación WAR (sin borrar nada)
FROM deps as package

WORKDIR /build

COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/*.war target/app.war

################################################################################

# Etapa 3: (ANTES extraía layertools → para WAR NO aplica)
# La dejamos pero vacía para no romper estructura
FROM package as extract

WORKDIR /build

# En WAR no existe jarmode=layertools, así que solo copiamos el WAR
RUN mkdir -p target/extracted && \
    cp target/app.war target/extracted/app.war

################################################################################

# Etapa 4 final: ejecutar WAR con Tomcat embebido o con Spring Boot Loader
FROM eclipse-temurin:17-jre-jammy AS final

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Copiar el WAR ya compilado
COPY --from=extract /build/target/extracted/app.war ./app.war

EXPOSE 8082

# WAR NO usa JarLauncher – usa el launcher normal
ENTRYPOINT [ "java", "-jar", "app.war" ]
