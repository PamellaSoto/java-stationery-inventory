FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/moonstationery-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 612

ENTRYPOINT ["java", "-jar", "app.jar"]
