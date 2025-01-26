#!/bin/bash
git checkout main

# Move to root
cd ..

# Move the current JAR to the archive directory
mv target/*.jar jars/

# Rebuild
mvn clean
mvn package

# Get the latest JAR filename
LATEST_JAR=$(ls target/*.jar)

# Copy the latest JAR to the project's root
cp "$LATEST_JAR" app.jar

# Remove temp file
rm target/*.original

# Print to console
echo 'JAR updated and archived successfully'
