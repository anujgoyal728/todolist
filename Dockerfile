# Use an official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/todolist-1.0-SNAPSHOT.jar app.jar

# Copy the configuration file into the container
COPY config.yml config.yml

# Expose the ports used by the application
EXPOSE 8080 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar", "server", "config.yml"]
