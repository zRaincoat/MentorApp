# Mentor-Mentee Management System

This is a **Java Spring Boot** application that allows the management of mentors and mentees via a **REST API**. It supports **CRUD** operations and integrates **Kafka** for messaging between mentors and mentees. The project connects to a **PostgreSQL** database and provides a `docker-compose` file to easily set up Kafka and Zookeeper.

## Table of Contents
- [Mentor-Mentee Management System](#mentor-mentee-management-system)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [1. Clone the Repository](#1-clone-the-repository)
  - [2. Configure PostgreSQL Connection](#2-configure-postgresql-connection)
  - [3. Run Kafka, Zookeeper, and the Docker Image](#3-run-kafka-zookeeper-and-the-docker-image)
  - [4. Build and Run the Application](#4-build-and-run-the-application)
  - [5. Postman Collection (recommended)](#5-postman-collection-recommended)
  - [6. API Endpoints](#6-api-endpoints)
  
## Features
- **CRUD Operations**: Manage mentors and mentees with endpoints for creating, reading, updating, and deleting records.
- **Kafka Integration**: Mentors can send messages to their mentees via Kafka.
- **PostgreSQL Database**: The application connects to a PostgreSQL database using `application.properties`.
- **Docker Support**: Kafka and Zookeeper are easily set up using Docker Compose.

## Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose
- PostgreSQL

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/zRaincoat/MentorApp.git
cd MentorApp
```

### 2. Configure PostgreSQL Connection
Update the `application.properties` file to configure the PostgreSQL connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
```

### 3. Run Kafka, Zookeeper and the Docker image
Use the provided `docker-compose.yml` file to run Kafka and Zookeeper as well as the Docker image. In the project directory, execute the following command:

```bash
docker-compose up
```

This will start Kafka on port `9092` and Zookeeper on port `2181`.

### 4. Build and Run the Application
You can build the application using Maven:

```bash
mvn clean install
```

Once built, run the application:

```bash
java -jar target/stefan-0.0.1-SNAPSHOT.jar
```
**Just press the Run StefanApplication button if using an IDE.
### 5. Postman Collection (recommended)
You can import the Postman collection to test the API endpoints. The collection is included in the repository as `MentorApp.postman_collection.json`.

### 6. API Endpoints
Here are the main endpoints of the application:

#### Mentors
- **Create Mentor**: `POST /api/mentors`
  ```json
  {
    "username": "mentor_john",
    "name": "John Smith",
    "password": "123",
    "expertise": "Java Development"
  }
  ```
- **Get Mentor by ID**: `GET /api/mentors/{id}`
- **Get Mentor by Username**: `GET /api/mentors/search/{username}`
- **Update Mentor**: `PUT /api/mentors/{id}`
- **Delete Mentor**: `DELETE /api/mentors/{id}`

#### Mentees
- **Create Mentee**: `POST /api/mentees`
  ```json
  {
    "username": "mentee_alex",
    "name": "Alex Wilson",
    "password": "123",
    "mentor": { "username": "mentor_john" }
  }
  ```
- **Get Mentee by ID**: `GET /api/mentees/{id}`
- **Get Mentee by Username**: `GET /api/mentees/search/{username}`
- **Update Mentee**: `PUT /api/mentees/{id}`
- **Delete Mentee**: `DELETE /api/mentees/{id}`

#### Kafka Messaging
- **Send Message from Mentor to Mentee**: `POST /api/mentors/messages/send`
  ```json
  {
    "mentorId": 1,
    "menteeId": 1,
    "messageContent": "Hello, mentee! How are you?"
  }
  ```

