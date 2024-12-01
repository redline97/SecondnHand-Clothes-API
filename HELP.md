# Project Setup and Usage Guide

Welcome to the project! Please follow the steps below to set up and run the application.

## Prerequisites
1. **Load the Project as a Maven Project**  
   Ensure that this project is imported as a Maven project in your IDE.

2. **Java 17 JDK**
    - Install Java 17 JDK on your machine.
    - Set the project structure to use Java 17 as the SDK.
    - Ensure the language level matches version 17.

3. **PostgreSQL Database**
    - Install PostgreSQL locally on your machine.
    - Create a database named `postgres`.
    - Set the database configurations with the following credentials:
        - Username: `user`
        - Password: `postgres`
    - For additional database configuration details, refer to the `application-local` configuration file.

4. **Liquibase Integration**
    - When the application is run for the first time, Liquibase will automatically create the required tables in the database.
    - Some predefined rows will also be inserted during this process.

## Build and Test
1. **Maven Repositories**  
   If Maven repositories are configured on your local machine, execute the following commands:
    - `mvn clean install`  
      This will clean the project, build it, and execute all predefined tests.
    - You can review the individual tests later if needed.

## Running the Application
1. **Active Profile**  
   Run the application with the `local` profile active.

## Available APIs
1. Publicly accessible APIs:
    - `/api/v1/clothes/list`
    - `/api/auth/login`

2. All other APIs require JWT validation.

## Authentication Credentials
- **Username:** `reddy`
- **Password:** `password`

Once logged in, you can use the provided JWT to access protected endpoints.

Enjoy exploring and working with the application! :)

