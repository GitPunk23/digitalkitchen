FROM openjdk:17-jdk

WORKDIR /app

# Copy all JAR files to /app directory and rename them to app.jar
COPY app.jar .

# Set app.jar as the CMD to be executed
CMD ["java", "-jar", "app.jar"]

