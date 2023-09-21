# Marvel API Client - ReadMe

## Overview

Marvel API Client is a Java application that serves as a REST client to consume the official Marvel API. This application is built using Java 17, Maven, Spring Framework 6, and Spring Boot. It allows you to interact with the Marvel API to retrieve information about Marvel characters, comics, and more.

### Marvel API Documentation

For detailed information about the Marvel API, visit the official documentation on [Marvel Developer Portal](https://developer.marvel.com/). You'll need to sign up for an API key to use this application.

### API Documentation (Swagger)

Explore the API using the interactive Swagger documentation: [API Documentation](http://localhost:8080/swagger-ui/index.html)

## Features

- Fetch information about Marvel characters.
- Retrieve details about Marvel comics.
- Secure authentication using JSON Web Tokens (JWT).
- Easy-to-use RESTful endpoints.

## API Endpoints

### Registration

#### Register a new user:

- Endpoint: `POST /auth/register`
- Request Body:
  ```json
  {
      "username": "admin",
      "password": "123456",
      "firstname": "Administrator",
      "lastname": "Marvel",
      "email": "admin@marvel.com",
      "phone": "79990001"
  }
  ```

### Authentication

#### Login to obtain JWT token:

- Endpoint: `POST /auth/login`
- Request Body:
  ```json
  {
      "username": "admin",
      "password": "admin"
  }
  ```

### Marvel API Endpoints

1. Find characters by name, comics, and series:

   - Endpoint: `GET /api/v1/characters`
   - Endpoint: `GET /api/v1/characters?limit=20&offset=40`
   - Endpoint: `GET /api/v1/characters?name={name}`
   - Endpoint: `GET /api/v1/characters?comics={comicId}`
   - Endpoint: `GET /api/v1/characters?series={serieId}`

2. Find a list of comics for a specific character:

   - Endpoint: `GET /api/v1/characters/{characterId}/comics`

3. Fetch description and image of a specific character:

   - Endpoint: `GET /api/v1/characters/{characterId}/image`

4. Show a full list of comics:

   - Endpoint: `GET /api/v1/comics`

5. Show a comic filtered by ID:

   - Endpoint: `GET /api/v1/comics/{comicId}`

## Prerequisites

Before running the Marvel API Client, ensure that you have the following prerequisites installed on your machine:

- Java 17 or later
- Maven
- MySQL database (if required for your configuration)

## Getting Started

Follow these steps to set up and run the Marvel API Client locally:

1. Clone the repository to your local machine:

   ```bash
   git clone https://csurio@bitbucket.org/csurio/marvel-api.git
   ```

2. Navigate to the project directory:

   ```bash
   cd marvel-api-client
   ```

3. Create a MySQL database (if required) and configure the database connection in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/marveldb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root_password
   ```

4. Build the application using Maven:

   ```bash
   mvn clean install
   ```

5. Start the application:

   ```bash
   java -jar target/marvel-api-0.0.1-SNAPSHOT.jar
   ```

The Marvel API Client should now be running locally.

## Usage

Once the application is running, you can access the API documentation at `http://localhost:8080/swagger-ui/index.html`, where you can explore and test the available endpoints.

To access secured endpoints, you will need to obtain a JWT token by registering or logging in. Use the token in the `Authorization` header with the value `Bearer your_token` for authentication.

## Deploying to a Server

To deploy the Marvel API Client to a server, follow these general steps:

1. Build the application as described above.

2. Copy the generated JAR file (`marvel-api-0.0.1-SNAPSHOT.jar`) to your server.

3. Ensure that Java 17 (or later) is installed on your server.

4. Configure your server environment (e.g., set environment variables, configure database access).

5. Start the application on your server:

   ```bash
   java -jar marvel-api-0.0.1-SNAPSHOT.jar.jar
   ```

The Marvel API Client should now be up and running on your server.

## Contributing

If you'd like to contribute to the Marvel API Client project, please follow our [contribution guidelines](CONTRIBUTING.md).

## License

Marvel API Client is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or need assistance, feel free to contact us at [csurio@hotmail.com](mailto:csurio@hotmail.com).

---

**Marvel API Client** - © 2023 Carlos Surio Developer