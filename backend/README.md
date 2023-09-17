## DEVELOPER SERVICE BACKEND

## API Gateway and Eureka Discovery Server

This project is built to work with API Gateway and Eureka Discovery Server. API Gateway is used to route incoming requests to the specified microservices, while Eureka Discovery Server is used for service discovery and management.

## Depo Service
The Depo Service is responsible for managing project codebase and requirements. It provides CRUD (Create, Read, Update, Delete) operations for projects and their corresponding requirements.

## User Service
The User Service handles user role and authority management. It provides CRUD operations to manage user roles and their associated authorities.

These two services work together to create a complete system where projects and their requirements can be managed, and user roles and authorities can be maintained efficiently.


## AuthController

### User Login
- **URL:** `POST /api/v1/auth/login`
- Description: Used for user login with a username and password.

### Refresh Token Login
- **URL:** `POST /api/v1/auth/refresh`
- Description: Used to obtain a new access token using a valid refresh token.

### Logout
- **URL:** `DELETE /api/v1/auth/logout`
- Description: Used to log out the user and invalidate the refresh token.

## CodebaseController

### Get All Codebases
- **URL:** `GET /api/v1/codebases`
- Description: Retrieves all code representations available in the system.

### Get Codebase by ID
- **URL:** `GET /api/v1/codebases/{id}`
- Description: Retrieves a specific code representation using its ID.

### Get Requirements for Codebase
- **URL:** `GET /api/v1/codebases/{id}/requirements`
- Description: Retrieves the requirements associated with a specific code representation.

### Link Codebase with Requirements
- **URL:** `PUT /api/v1/codebases/{id}/requirements`
- Description: Links a specific code representation with requirements.

### Create New Codebase
- **URL:** `POST /api/v1/codebases`
- Description: Creates a new code representation.

### Update Codebase
- **URL:** `PUT /api/v1/codebases`
- Description: Updates an existing code representation.

### Delete Codebase
- **URL:** `DELETE /api/v1/codebases/{id}`
- Description: Deletes a specific code representation.

### Delete Requirement Link from Codebase
- **URL:** `DELETE /api/v1/codebases/{id}/requirements/{requirementId}`
- Description: Deletes a specific requirement link from a code representation.

## RequirementController

### Get All Requirements
- **URL:** `GET /api/v1/requirements`
- Description: Retrieves all requirements available in the system.

### Get Requirement by ID
- **URL:** `GET /api/v1/requirements/{id}`
- Description: Retrieves a specific requirement using its ID.

### Create New Requirement
- **URL:** `POST /api/v1/requirements`
- Description: Creates a new requirement.

### Update Requirement
- **URL:** `PUT /api/v1/requirements`
- Description: Updates an existing requirement.

### Delete Requirement
- **URL:** `DELETE /api/v1/requirements/{id}`
- Description: Deletes a specific requirement.

## UserController

### Get All Users
- **URL:** `GET /api/v1/users`
- Description: Retrieves all users available in the system.

### Get User by ID
- **URL:** `GET /api/v1/users/{id}`
- Description: Retrieves a specific user using its ID.

### Create New User
- **URL:** `POST /api/v1/users`
- Description: Creates a new user.

### Update User
- **URL:** `PUT /api/v1/users`
- Description: Updates an existing user.

### Delete User
- **URL:** `DELETE /api/v1/users/{id}`
- Description: Deletes a specific user.

## Getting Started

To download and run the project code, follow the steps below:

### Prerequisites

- Make sure Docker is installed on your computer.

# API Endpoint Descriptions

This README.md file provides a brief overview of the API's endpoints, their URLs, and their functionalities.

### Project Setup

1. Clone the project repository:
2. docker-compose up



