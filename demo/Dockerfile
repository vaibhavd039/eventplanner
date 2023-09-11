# Use a base image with Java and Maven for building
FROM maven:3.8.4-jdk-11 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Create a smaller image with just the JAR file
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=builder /app/target/your-application.jar .

# Install Redis client
RUN apt-get update && apt-get install -y redis-tools

# Install ZooKeeper client
RUN apt-get update && apt-get install -y zookeeper

# Install MongoDB client
RUN apt-get update && apt-get install -y mongodb-clients

# Expose your application's port
EXPOSE 8080

# Start your Spring Boot application
CMD ["java", "-jar", "your-application.jar"]
