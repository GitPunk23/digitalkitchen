#!/bin/bash

# Move to root
cd ..

# Move the current JAR to the archive directory
mv *.jar archive/

# Get the latest JAR filename
LATEST_JAR=$(ls target/*.jar)

# Move the latest JAR to the project's root
mv "$LATEST_JAR" .

# Print to console
echo 'JAR updated and archived successfully'
