# Patient Management System

## Project Overview

The Patient Management System is a Java microservices backend project designed to manage patients, billing, and analytics for a healthcare application. This project utilizes a microservices architecture to ensure scalability and maintainability.

## Microservices Architecture

The system is divided into the following microservices:

1. **Patient Service**: Manages patient records, including creation, updating, and retrieval of patient information.
2. **Billing Service**: Handles billing information and transactions associated with patient services.
3. **Analytics Service**: Provides analytical insights into patient data and service utilization.
4. **API Gateway**: Acts as a single entry point for various microservices, handling requests and routing them appropriately.

## Tech Stack

- **Java 17**
- **Spring Boot 3.4.x**
- **gRPC** for communication between microservices
- **Kafka** for event streaming
- **PostgreSQL** as the database
- **Docker** for containerization

## Installation Instructions

### Docker Setup
1. Ensure Docker is installed on your machine.
2. Clone the repository:
   ```bash
   git clone https://github.com/IsuruKavi/Patient-management-system.git
   cd Patient-management-system
   ```
3. Build the Docker images:
   ```bash
   docker-compose build
   ```
4. Run the Docker containers:
   ```bash
   docker-compose up
   ```

### Manual Setup
1. Ensure you have Java 17 and Maven installed.
2. Clone the repository:
   ```bash
   git clone https://github.com/IsuruKavi/Patient-management-system.git
   cd Patient-management-system
   ```
3. For each microservice, navigate to the microservice directory and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### Patient Service
- **GET** `/patients`: Retrieve all patients
- **POST** `/patients`: Create a new patient

### Billing Service
- **GET** `/billing/{id}`: Retrieve billing information by patient ID
- **POST** `/billing`: Create a new billing entry

### Analytics Service
- **GET** `/analytics`: Retrieve analytics data

## Deployment Guide

1. Choose a cloud provider and set up the necessary infrastructure.
2. Deploy each microservice using the Docker images created.
3. Configure the API Gateway to route requests to the appropriate services.

## Troubleshooting Tips
- Ensure all services are running correctly by checking Docker logs: `docker-compose logs`
- Verify database connections if any service is unable to start.
- Check API Gateway routing rules if endpoints are not responding as expected.

## License
This project is licensed under the MIT License.