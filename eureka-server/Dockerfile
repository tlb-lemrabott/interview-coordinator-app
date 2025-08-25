FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file
COPY target/eureka-server-*.jar app.jar

# Expose the port
EXPOSE 8761

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
