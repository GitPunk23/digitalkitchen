FROM openjdk:17-jdk

WORKDIR /app

COPY CICD/app.jar .
COPY .env /app/.env
COPY keystore/ /app/keystore/

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
