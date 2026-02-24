FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN ./gradlew build -x test || true
EXPOSE 43594
CMD ["./gradlew", "run"]
