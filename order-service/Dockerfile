FROM gradle:8.14-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle

RUN gradle build -x test --no-daemon || true

COPY src ./src

RUN gradle clean build --no-daemon

FROM eclipse-temurin:21-jre AS runner

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
