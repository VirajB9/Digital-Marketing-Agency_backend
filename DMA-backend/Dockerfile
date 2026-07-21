FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN ./gradlew clean bootJar -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/dma-backend-0.0.1-SNAPSHOT.jar"]