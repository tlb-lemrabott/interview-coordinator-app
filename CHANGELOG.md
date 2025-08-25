# Changelog

All notable changes to the Interview Coordinator Application will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Initial project setup with microservices architecture
- Eureka Server for service discovery
- API Gateway Service for request routing
- Candidate Service for candidate management
- Recruiter Service for recruiter management
- Interview Service for interview coordination
- RabbitMQ integration for asynchronous messaging
- PostgreSQL database integration
- Docker Compose setup for development
- Comprehensive documentation and contributing guidelines

### Changed
- N/A

### Deprecated
- N/A

### Removed
- N/A

### Fixed
- N/A

### Security
- N/A

## [1.0.0] - 2024-01-XX

### Added
- Initial release of Interview Coordinator Application
- Microservices architecture with 5 core services
- Service discovery using Netflix Eureka
- API Gateway for centralized request handling
- RabbitMQ messaging for inter-service communication
- PostgreSQL database for data persistence
- RESTful APIs for all services
- Basic CRUD operations for candidates, recruiters, and interviews
- Availability management for recruiters
- Interview scheduling functionality
- Docker containerization support
- Maven multi-module project structure
- Comprehensive documentation

### Technical Stack
- Spring Boot 3.4.4
- Spring Cloud 2024.0.1
- Java 17
- Maven
- PostgreSQL
- RabbitMQ
- Docker

### Services
- **Eureka Server** (Port: 8761) - Service discovery
- **API Gateway** (Port: 8080) - Request routing
- **Candidate Service** (Port: 8081) - Candidate management
- **Recruiter Service** (Port: 8082) - Recruiter management
- **Interview Service** (Port: 8083) - Interview coordination

---

## Version History

- **1.0.0** - Initial release with basic microservices architecture
- **Future versions** - Will be added as features are developed

## Contributing

When adding new entries to this changelog, please follow the established format and include:

1. **Type of change** (Added, Changed, Deprecated, Removed, Fixed, Security)
2. **Clear description** of what was changed
3. **Service affected** if applicable
4. **Breaking changes** if any
5. **Migration notes** if needed

## Links

- [GitHub Repository](https://github.com/your-org/interview-coordinator-app)
- [Documentation](README.md)
- [Contributing Guidelines](CONTRIBUTING.md)
