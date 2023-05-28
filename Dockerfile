FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:17-slim

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]