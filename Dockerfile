FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /shorter
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /shorter
COPY --from=build /shorter/target/karpenShorter-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]