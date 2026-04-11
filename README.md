# 🪐 Jupiter Service

> The productivity core of your microservices ecosystem.

**Jupiter** is a robust task management microservice (To-Do) designed for scalability and high availability. Built with **Spring Boot** and **Spring Cloud**, it operates seamlessly within a microservices architecture using **Netflix Eureka** for service discovery and orchestration.

## 🚀 Key Features

- ✅ Task Management: Full CRUD operations for tasks linked to a userId.
- 📊 Dynamic Status: Control states (Pending, In Progress, Completed).
- 🔐 Multi-tenant Ready: Structured for data isolation per user.
- 📡 Service Discovery: Automatic registration with Eureka Server.

## 🛠️ Tech Stack

- Language: Java 17+
- Framework: Spring Boot 3.x
- Cloud/Orchestration: Spring Cloud (Netflix Eureka Client)
- Database: PostgreSQL (or H2 for development)
- Persistence: Spring Data JPA
- Documentation: Swagger / OpenAPI

## 📐 Architecture

Jupiter is designed to be a piece of a larger puzzle:

1. Config Client: Fetches centralized configurations.
2. Eureka Client: Reports its heartbeat to the naming server.
3. API Gateway: Recommended for filtering external requests before reaching Jupiter.

## ⚙️ Running the Project

Prerequisite: Ensure your Eureka Server is running on default port 8761.

1. Clone the repository from GitHub.
2. Update your application.yml with your database credentials.
3. Run the project using your IDE or via terminal with the Maven wrapper command.

## 📋 Main Endpoints


| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | /tasks | Lists all tasks for the authenticated user. |
| POST | /tasks | Creates a new task. |
| PUT | /tasks/{id} | Updates task status or content. |
| DELETE | /tasks/{id} | Removes a task. |

---
Built with ☕ and a focus on scalability.
