FROM maven:3.9.2-jdk-21 AS build

WORKDIR /app
COPY pom.xml mvnw ./
COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/moonstationery-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 612
ENTRYPOINT ["java", "-jar", "app.jar"]
