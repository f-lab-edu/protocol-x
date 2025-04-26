FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /workspace/app

ARG MODULE

COPY gradle gradle
COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY ${MODULE}/build.gradle.kts ${MODULE}/

RUN chmod +x ./gradlew && ./gradlew ${MODULE}:dependencies --no-daemon

COPY ${MODULE} ${MODULE}

RUN ./gradlew ${MODULE}:build -x test --no-daemon

FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp

ARG MODULE
COPY --from=build /workspace/app/${MODULE}/build/libs/*.jar app.jar

# non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
RUN chown -R appuser:appgroup /app.jar
USER appuser

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-XX:+UseParallelGC", "-jar", "/app.jar"] 
