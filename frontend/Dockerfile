# Use an official Node.js runtime as a parent image
FROM node:16-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the package.json and package-lock.json files to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code to the container
COPY . .

# Build the application for production
RUN npm run build

# Install a production-ready server (e.g., Nginx)
# For simplicity, we'll use a basic HTTP server provided by http-server
RUN npm install -g http-server

# Expose port 8080 (or any other port you wish to use)
EXPOSE 8080

# Start the application using the production-ready server
CMD ["http-server", "build", "-a", "0.0.0.0", "--proxy", "http://localhost:8080?"]
