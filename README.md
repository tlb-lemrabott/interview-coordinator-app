# Interview Coordinator Application

A microservices-based application for managing interview coordination between candidates and recruiters.

## 🏗️ Architecture

This application follows a microservices architecture pattern with the following components:

### Services

1. **Eureka Server** (`eureka-server/`)
   - Service discovery and registration
   - Central registry for all microservices

2. **API Gateway Service** (`APIGatewayService/`)
   - Single entry point for all client requests
   - Route requests to appropriate microservices
   - Load balancing and cross-cutting concerns

3. **Candidate Service** (`candidate-service/`)
   - Manages candidate information and profiles
   - Handles candidate registration and updates
   - Publishes candidate events via RabbitMQ

4. **Recruiter Service** (`recruiter-service/`)
   - Manages recruiter profiles and availability
   - Handles interview scheduling requests
   - Listens to candidate events and responds with availability

5. **Interview Service** (`interview-service/`)
   - Manages interview scheduling and results
   - Coordinates between candidates and recruiters
   - Handles interview lifecycle management

## 🚀 Technology Stack

- **Framework**: Spring Boot 3.4.4
- **Java Version**: 17
- **Service Discovery**: Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Messaging**: RabbitMQ
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Cloud**: Spring Cloud 2024.0.1

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL
- RabbitMQ
- Git

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd interview-coordinator-app
```

### 2. Database Setup

Create PostgreSQL databases for each service:

```sql
CREATE DATABASE candidate_db;
CREATE DATABASE recruiter_db;
CREATE DATABASE interview_db;
```

### 3. RabbitMQ Setup

Ensure RabbitMQ is running on your system:

```bash
# Using Docker
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management

# Or install locally
# Follow RabbitMQ installation guide for your OS
```

### 4. Configuration

Update application properties for each service with your database and RabbitMQ configurations.

### 5. Build and Run

#### Option 1: Run All Services

```bash
# Build all services
mvn clean install -DskipTests

# Start services in order
cd eureka-server && mvn spring-boot:run
cd ../APIGatewayService && mvn spring-boot:run
cd ../candidate-service && mvn spring-boot:run
cd ../recruiter-service && mvn spring-boot:run
cd ../interview-service && mvn spring-boot:run
```

#### Option 2: Using Docker Compose (if available)

```bash
docker-compose up -d
```

## 🌐 Service Endpoints

### Eureka Server
- **URL**: http://localhost:8761
- **Purpose**: Service discovery dashboard

### API Gateway
- **URL**: http://localhost:8080
- **Purpose**: Main entry point for all API calls

### Candidate Service
- **Port**: 8081
- **Endpoints**: `/api/candidates/*`

### Recruiter Service
- **Port**: 8082
- **Endpoints**: `/api/recruiters/*`

### Interview Service
- **Port**: 8083
- **Endpoints**: `/api/interviews/*`

## 🔄 Message Flow

1. **Candidate Registration**: Candidate service publishes candidate events
2. **Availability Check**: Recruiter service listens and responds with availability
3. **Interview Scheduling**: Interview service coordinates the scheduling process
4. **Result Management**: Interview service manages interview results

## 🧪 Testing

Run tests for all services:

```bash
# Run all tests
mvn test

# Run specific service tests
cd candidate-service && mvn test
```

## 📦 Deployment

### Development
- Use individual Maven commands to run services
- Configure local database and RabbitMQ instances

### Production
- Use Docker containers for each service
- Configure production databases and message queues
- Set up proper monitoring and logging

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation in each service directory

## 🔄 Version History

- **v1.0.0** - Initial release with basic microservices architecture
- **v1.1.0** - Added RabbitMQ messaging
- **v1.2.0** - Enhanced interview coordination features

---

**Note**: This is a microservices application. Ensure all services are running for the complete functionality to work properly.
