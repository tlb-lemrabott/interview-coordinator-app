# Contributing to Interview Coordinator Application

Thank you for your interest in contributing to the Interview Coordinator Application! This document provides guidelines and best practices for contributing to this microservices project.

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Git
- Docker (optional, for containerized development)
- PostgreSQL
- RabbitMQ

### Development Setup
1. Fork the repository
2. Clone your fork locally
3. Set up the development environment
4. Create a feature branch
5. Make your changes
6. Test your changes
7. Submit a pull request

## 🌿 Git Workflow

### Branch Naming Convention

We follow a structured branch naming convention:

- **Feature branches**: `feature/description-of-feature`
- **Bug fixes**: `bugfix/description-of-bug`
- **Hotfixes**: `hotfix/description-of-hotfix`
- **Release branches**: `release/version-number`
- **Service-specific features**: `feature/service-name/description`

Examples:
- `feature/candidate-service/add-email-validation`
- `bugfix/recruiter-service/fix-availability-calculation`
- `hotfix/api-gateway/fix-cors-issue`
- `feature/interview-service/implement-scheduling-algorithm`

### Commit Message Convention

We follow the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

#### Types:
- **feat**: A new feature
- **fix**: A bug fix
- **docs**: Documentation only changes
- **style**: Changes that do not affect the meaning of the code
- **refactor**: A code change that neither fixes a bug nor adds a feature
- **perf**: A code change that improves performance
- **test**: Adding missing tests or correcting existing tests
- **chore**: Changes to the build process or auxiliary tools

#### Examples:
```
feat(candidate-service): add email validation for candidate registration

fix(recruiter-service): resolve availability slot calculation bug

docs: update API documentation for interview endpoints

refactor(api-gateway): improve error handling middleware

test(interview-service): add unit tests for scheduling logic
```

### Pull Request Process

1. **Create a feature branch** from `main`
2. **Make your changes** following the coding standards
3. **Write/update tests** for your changes
4. **Update documentation** if necessary
5. **Ensure all tests pass** locally
6. **Create a pull request** with a clear description
7. **Request review** from team members
8. **Address feedback** and make necessary changes
9. **Merge** after approval

### Pull Request Template

```markdown
## Description
Brief description of the changes made.

## Type of Change
- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] Documentation update

## Service(s) Affected
- [ ] Eureka Server
- [ ] API Gateway Service
- [ ] Candidate Service
- [ ] Recruiter Service
- [ ] Interview Service

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing completed

## Checklist
- [ ] My code follows the style guidelines of this project
- [ ] I have performed a self-review of my own code
- [ ] I have commented my code, particularly in hard-to-understand areas
- [ ] I have made corresponding changes to the documentation
- [ ] My changes generate no new warnings
- [ ] I have added tests that prove my fix is effective or that my feature works
- [ ] New and existing unit tests pass locally with my changes
- [ ] Any dependent changes have been merged and published in downstream modules
```

## 🏗️ Development Guidelines

### Code Style

- Follow Java coding conventions
- Use meaningful variable and method names
- Keep methods small and focused
- Add comments for complex logic
- Use Lombok annotations where appropriate
- Follow Spring Boot best practices

### Microservices Best Practices

- **Service Independence**: Each service should be independently deployable
- **Database per Service**: Each service should have its own database
- **API Design**: Use RESTful principles for API design
- **Error Handling**: Implement proper error handling and logging
- **Configuration**: Use externalized configuration
- **Monitoring**: Add health checks and metrics

### Testing Strategy

- **Unit Tests**: Test individual components in isolation
- **Integration Tests**: Test service interactions
- **End-to-End Tests**: Test complete workflows
- **Performance Tests**: Ensure acceptable performance under load

### Security Guidelines

- Never commit sensitive information (passwords, API keys)
- Use environment variables for configuration
- Implement proper authentication and authorization
- Validate all inputs
- Use HTTPS in production
- Keep dependencies updated

## 📋 Issue Reporting

### Bug Reports

When reporting bugs, please include:

1. **Clear description** of the bug
2. **Steps to reproduce** the issue
3. **Expected behavior** vs actual behavior
4. **Environment details** (OS, Java version, etc.)
5. **Screenshots** if applicable
6. **Logs** if available

### Feature Requests

When requesting features, please include:

1. **Clear description** of the feature
2. **Use case** and benefits
3. **Proposed implementation** (if you have ideas)
4. **Priority** level

## 🔄 Release Process

### Versioning

We follow [Semantic Versioning](https://semver.org/):

- **MAJOR**: Incompatible API changes
- **MINOR**: New functionality in a backward-compatible manner
- **PATCH**: Backward-compatible bug fixes

### Release Steps

1. Create a release branch from `main`
2. Update version numbers in all services
3. Update CHANGELOG.md
4. Run full test suite
5. Create a pull request to merge release branch
6. After approval, create a release tag
7. Deploy to staging environment
8. Deploy to production environment

## 🆘 Getting Help

- **Documentation**: Check the README and service-specific documentation
- **Issues**: Search existing issues before creating new ones
- **Discussions**: Use GitHub Discussions for questions and ideas
- **Team**: Reach out to the development team

## 📝 Code of Conduct

- Be respectful and inclusive
- Focus on the code, not the person
- Provide constructive feedback
- Help others learn and grow
- Follow the project's coding standards

## 🎯 Contribution Areas

We welcome contributions in the following areas:

- **New Features**: Adding new functionality to existing services
- **Bug Fixes**: Resolving issues and improving stability
- **Documentation**: Improving README, API docs, and guides
- **Testing**: Adding tests and improving test coverage
- **Performance**: Optimizing code and improving efficiency
- **Security**: Identifying and fixing security vulnerabilities
- **DevOps**: Improving deployment and CI/CD processes

Thank you for contributing to the Interview Coordinator Application! 🚀
