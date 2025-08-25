#!/bin/bash

# Interview Coordinator Application - Development Setup Script
# This script sets up the development environment for the microservices application

set -e

echo "🚀 Setting up Interview Coordinator Application Development Environment"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if required tools are installed
check_requirements() {
    print_status "Checking system requirements..."
    
    # Check Java
    if ! command -v java &> /dev/null; then
        print_error "Java is not installed. Please install Java 17 or higher."
        exit 1
    fi
    
    java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
    if [ "$java_version" -lt 17 ]; then
        print_error "Java version $java_version is too old. Please install Java 17 or higher."
        exit 1
    fi
    print_success "Java version $(java -version 2>&1 | head -n 1 | cut -d'"' -f2) found"
    
    # Check Maven
    if ! command -v mvn &> /dev/null; then
        print_error "Maven is not installed. Please install Maven 3.6+."
        exit 1
    fi
    print_success "Maven version $(mvn -version | head -n 1 | cut -d' ' -f3) found"
    
    # Check Docker (optional)
    if command -v docker &> /dev/null; then
        print_success "Docker found"
    else
        print_warning "Docker not found. You can still run services individually with Maven."
    fi
    
    # Check Git
    if ! command -v git &> /dev/null; then
        print_error "Git is not installed. Please install Git."
        exit 1
    fi
    print_success "Git found"
}

# Create necessary directories
create_directories() {
    print_status "Creating necessary directories..."
    
    mkdir -p logs
    mkdir -p config
    mkdir -p scripts
    
    print_success "Directories created"
}

# Setup databases
setup_databases() {
    print_status "Setting up databases..."
    
    # Check if PostgreSQL is running
    if command -v psql &> /dev/null; then
        print_status "PostgreSQL found, attempting to create databases..."
        
        # Create databases (you may need to adjust connection details)
        psql -h localhost -U postgres -c "CREATE DATABASE candidate_db;" 2>/dev/null || print_warning "Could not create candidate_db (may already exist)"
        psql -h localhost -U postgres -c "CREATE DATABASE recruiter_db;" 2>/dev/null || print_warning "Could not create recruiter_db (may already exist)"
        psql -h localhost -U postgres -c "CREATE DATABASE interview_db;" 2>/dev/null || print_warning "Could not create interview_db (may already exist)"
        
        print_success "Database setup completed"
    else
        print_warning "PostgreSQL not found. Please install and configure PostgreSQL manually."
        print_status "Required databases: candidate_db, recruiter_db, interview_db"
    fi
}

# Build the project
build_project() {
    print_status "Building the project..."
    
    # Clean and install
    mvn clean install -DskipTests
    
    print_success "Project built successfully"
}

# Create environment files
create_env_files() {
    print_status "Creating environment configuration files..."
    
    # Create application-local.properties for each service
    cat > eureka-server/src/main/resources/application-local.properties << EOF
# Local development configuration for Eureka Server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=DEBUG
EOF

    cat > APIGatewayService/src/main/resources/application-local.properties << EOF
# Local development configuration for API Gateway
server.port=8080
spring.cloud.gateway.discovery.locator.enabled=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
logging.level.org.springframework.cloud.gateway=DEBUG
EOF

    cat > candidate-service/src/main/resources/application-local.properties << EOF
# Local development configuration for Candidate Service
server.port=8081
spring.datasource.url=jdbc:postgresql://localhost:5432/candidate_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin
EOF

    cat > recruiter-service/src/main/resources/application-local.properties << EOF
# Local development configuration for Recruiter Service
server.port=8082
spring.datasource.url=jdbc:postgresql://localhost:5432/recruiter_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin
EOF

    cat > interview-service/src/main/resources/application-local.properties << EOF
# Local development configuration for Interview Service
server.port=8083
spring.datasource.url=jdbc:postgresql://localhost:5432/interview_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=admin
EOF

    print_success "Environment configuration files created"
}

# Create startup scripts
create_startup_scripts() {
    print_status "Creating startup scripts..."
    
    # Create script to start all services
    cat > scripts/start-all.sh << 'EOF'
#!/bin/bash

# Start all services in the correct order
echo "🚀 Starting Interview Coordinator Application..."

# Start Eureka Server
echo "Starting Eureka Server..."
cd eureka-server && mvn spring-boot:run -Dspring-boot.run.profiles=local > ../logs/eureka-server.log 2>&1 &
EUREKA_PID=$!
echo "Eureka Server started with PID: $EUREKA_PID"

# Wait for Eureka to start
sleep 10

# Start API Gateway
echo "Starting API Gateway..."
cd ../APIGatewayService && mvn spring-boot:run -Dspring-boot.run.profiles=local > ../logs/api-gateway.log 2>&1 &
GATEWAY_PID=$!
echo "API Gateway started with PID: $GATEWAY_PID"

# Wait for Gateway to start
sleep 10

# Start Candidate Service
echo "Starting Candidate Service..."
cd ../candidate-service && mvn spring-boot:run -Dspring-boot.run.profiles=local > ../logs/candidate-service.log 2>&1 &
CANDIDATE_PID=$!
echo "Candidate Service started with PID: $CANDIDATE_PID"

# Start Recruiter Service
echo "Starting Recruiter Service..."
cd ../recruiter-service && mvn spring-boot:run -Dspring-boot.run.profiles=local > ../logs/recruiter-service.log 2>&1 &
RECRUITER_PID=$!
echo "Recruiter Service started with PID: $RECRUITER_PID"

# Start Interview Service
echo "Starting Interview Service..."
cd ../interview-service && mvn spring-boot:run -Dspring-boot.run.profiles=local > ../logs/interview-service.log 2>&1 &
INTERVIEW_PID=$!
echo "Interview Service started with PID: $INTERVIEW_PID"

# Save PIDs to file for later cleanup
echo "$EUREKA_PID $GATEWAY_PID $CANDIDATE_PID $RECRUITER_PID $INTERVIEW_PID" > ../logs/service-pids.txt

echo "✅ All services started successfully!"
echo "📊 Service URLs:"
echo "   Eureka Server: http://localhost:8761"
echo "   API Gateway: http://localhost:8080"
echo "   Candidate Service: http://localhost:8081"
echo "   Recruiter Service: http://localhost:8082"
echo "   Interview Service: http://localhost:8083"
echo ""
echo "📝 Logs are available in the logs/ directory"
echo "🛑 To stop all services, run: ./scripts/stop-all.sh"
EOF

    # Create script to stop all services
    cat > scripts/stop-all.sh << 'EOF'
#!/bin/bash

echo "🛑 Stopping all services..."

if [ -f logs/service-pids.txt ]; then
    PIDS=$(cat logs/service-pids.txt)
    for PID in $PIDS; do
        if kill -0 $PID 2>/dev/null; then
            echo "Stopping process $PID..."
            kill $PID
        fi
    done
    rm logs/service-pids.txt
    echo "✅ All services stopped"
else
    echo "No service PIDs found. Stopping Java processes..."
    pkill -f "spring-boot:run" || true
    echo "✅ Java processes stopped"
fi
EOF

    # Make scripts executable
    chmod +x scripts/start-all.sh
    chmod +x scripts/stop-all.sh
    
    print_success "Startup scripts created"
}

# Main execution
main() {
    echo "=========================================="
    echo "Interview Coordinator Application Setup"
    echo "=========================================="
    
    check_requirements
    create_directories
    setup_databases
    build_project
    create_env_files
    create_startup_scripts
    
    echo ""
    echo "=========================================="
    print_success "Setup completed successfully!"
    echo "=========================================="
    echo ""
    echo "Next steps:"
    echo "1. Ensure PostgreSQL is running and accessible"
    echo "2. Ensure RabbitMQ is running (optional, for messaging)"
    echo "3. Start all services: ./scripts/start-all.sh"
    echo "4. Stop all services: ./scripts/stop-all.sh"
    echo ""
    echo "For more information, see README.md"
}

# Run main function
main "$@"
