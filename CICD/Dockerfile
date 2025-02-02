FROM openjdk:17-jdk

WORKDIR /app

COPY app.jar .
COPY ../.env /app/.env

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
