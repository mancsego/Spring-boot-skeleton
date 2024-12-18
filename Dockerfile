FROM openjdk:23-slim AS builder
WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle.kts ./
COPY src ./src

RUN ./gradlew clean build -x test

# production target
FROM builder AS prod
EXPOSE 8080

COPY --from=builder /app/build/libs/app.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# development target
FROM builder AS dev

RUN apt-get update && \
    useradd -m spring && \
    chown -R spring:spring /app && \
    chmod -R 755 /app && \
    mkdir -p /home/spring/.gradle && \
    chown -R spring:spring /home/spring

USER spring

CMD ["./gradlew", "bootRun"]