FROM openjdk:17-jdk-alpine AS builder

WORKDIR /app

COPY ./build.gradle.kts .
COPY ./settings.gradle.kts .
COPY ./gradle/ ./gradle
COPY ./.gradle ./.gradle
COPY ./gradlew .
RUN ./gradlew dependencies

# Copy our Kotlin code into the container
COPY src/ ./src
RUN ./gradlew --build-cache bootJar

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]