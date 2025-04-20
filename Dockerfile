FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /workspace/app

ARG MODULE

COPY gradle gradle
COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY ${MODULE}/build.gradle.kts ${MODULE}/

RUN chmod +x ./gradlew && ./gradlew ${MODULE}:dependencies --no-daemon

COPY ${MODULE} ${MODULE}
COPY common common

RUN ./gradlew ${MODULE}:build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp

ARG MODULE
COPY --from=build /workspace/app/${MODULE}/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-XX:+UseParallelGC", "-jar", "/app.jar"] 