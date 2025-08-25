FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file
COPY target/APIGatewayService-*.jar app.jar

# Expose the port
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
