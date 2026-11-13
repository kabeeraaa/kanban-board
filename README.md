# Kanban Board

NIIT capstone for organizing tasks across workflow columns. The Angular client uses REST APIs to create boards, manage team members, and save task status changes when a card is dropped into another column.

## Project layout

- `frontend/` contains the Angular and TypeScript client with Angular Material and CDK drag-and-drop.
- `backend/` contains the Java 11 and Spring Boot services, including an API gateway, service discovery, JWT authentication, board and task management, and notifications.
- MongoDB stores board and task data. MySQL backs the authentication service. RabbitMQ carries notification messages.

## Local setup

Use a Java 11 JDK, Maven 3, a Node.js version compatible with Angular 16, npm, and local instances of MySQL, MongoDB, and RabbitMQ.

1. Create a MySQL database named `kanban` and a database user with access to it.
2. Set `KANBAN_DB_USER` and `KANBAN_DB_PASSWORD` in the authentication service's environment. Set `KANBAN_DB_URL` if the database is somewhere other than `jdbc:mysql://localhost:3306/kanban`.
3. Set `KANBAN_JWT_SECRET` to the same base64-encoded random key of at least 32 bytes in both the authentication and board services. Keep these values local.
4. Start the backend modules in separate terminals. Run `mvn spring-boot:run` from each module directory listed below.

| Start order | Module | Local port |
| --- | --- | --- |
| 1 | `backend/EurekaServer` | 8761 |
| 2 | `backend/UserAuthenticationService` | 8083 |
| 3 | `backend/kanbanService` | 8084 |
| 4 | `backend/NotificationService` | 8085 |
| 5 | `backend/SpringCloudAPIGateway` | 9000 |

5. In `frontend/`, run `npm ci` and `npm start`. Open `http://localhost:4200` and register with a fictional account.

This repository is a local development demo. Use disposable data when trying it out.
