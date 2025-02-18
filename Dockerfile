FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/website-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]