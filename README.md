# YourSupplierApp API

Welcome to the YourSupplierApp API! This API provides endpoints for managing various entities in your application, including users, roles, products, payments, warehouses, and more.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Endpoints](#endpoints)
    - [Group](#group)
    - [Health Check](#health-check)
    - [Payment](#payment)
    - [Product](#product)
    - [Role](#role)
    - [User](#user)
    - [Warehouse](#warehouse)

## Getting Started

### Prerequisites

- Java 8
- Spring Boot
- Maven

### Installation

1. Clone this repository to your local machine.
2. Make sure you have Java 8 and Maven installed.
3. Navigate to the root directory of the cloned repository in your terminal.
4. Run the following command to build and run the application:

   ```shell
   mvn spring-boot:run
   ```
### The application will start and be accessible at 

  ```shell
   http://localhost:8080.
  ``` 
## EndPoints
### Group
#### - POST /api/group: 
- Create a new group.
#### - Swagger:
-  For detailed documentation and testing, refer to the Swagger UI.
## - Payment:
#### POST /api/payment: 
- Create a new payment.
#### GET /api/payment: -
Get a list of all payments.
#### GET /api/payment/{id}: 
- Get details of a specific payment.
#### PUT /api/payment/{id}: 
- Update a payment.
#### DELETE /api/payment/{id}: 
- Delete a payment.
#### Swagger: 
- For detailed documentation and testing, refer to the Swagger UI.

## - Product
#### POST /api/product: 
- Create a new product.
#### GET /api/product: 
- Get a list of all products.
#### GET /api/product/{id}: 
- Get details of a specific product.
#### PUT /api/product/{id}: 
- Update a product.
#### DELETE /api/product/{id}: 
- Delete a product.
#### Swagger: 
- For detailed documentation and testing, refer to the Swagger UI.

## Role
#### POST /api/role: 
- Create a new role.
#### GET /api/role: 
- Get a list of all roles.
#### GET /api/role/{id}: 
- Get details of a specific role.
#### PUT /api/role/{id}: 
- Update a role.
#### DELETE /api/role/{id}: 
- Delete a role.
#### Swagger: 
- For detailed documentation and testing, refer to the Swagger UI.
## User
#### POST /api/user: 
- Create a new user.
#### GET /api/user: 
- Get a list of all users.
#### GET /api/user/{id}: 
- Get details of a specific user.
#### PUT /api/user/{id}: 
- Update a user.
#### DELETE /api/user/{id}: 
- Delete a user.
#### Swagger: 
- For detailed documentation and testing, refer to the Swagger UI.
## Warehouse
#### POST /api/warehouse: 
- Create a new warehouse.
#### GET /api/warehouse: 
- Get a list of all warehouses.
#### GET /api/warehouse/{id}: 
- Get details of a specific warehouse.
#### PUT /api/warehouse/{id}: 
- Update a warehouse.
#### DELETE /api/warehouse/{id}: 
- Delete a warehouse.
#### Swagger: 
- For detailed documentation and testing, refer to the Swagger UI.

## Contributing
- Contributions are always welcome!

- Please ensure to follow good programming practices.