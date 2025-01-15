# Movie_Ratings_Catalog_System

**Video Demo and Explanation**: https://drive.google.com/file/d/1gfFiZTc0KzAVTMQkJycqaPQwsSYTvoLW/view?usp=sharing

## Project Overview

This project demonstrates a microservices-based architecture to manage and serve movie-related data. The system consists of the following services:

## 1. **Movie Info Service**
- **Description**: Provides information about movies.
- **Endpoint(s)**:
  - `/movies/{movieId}`: Returns details (e.g., description) for a given `movieId`.
- **Data**: Stores `movieId` and corresponding descriptions.

---

## 2. **Ratings Data Service**
- **Description**: Provides user-specific movie ratings.
- **Endpoint(s)**:
  - `/ratings/{userId}`: Returns ratings for all movies rated by the specified `userId`.
- **Data**: Stores user IDs, movie IDs, and their corresponding ratings.

---

## 3. **Movie Catalog Service**
- **Description**: Aggregates data from the `Movie Info Service` and `Ratings Data Service` to provide a catalog of movies rated by a specific user.
- **Workflow**:
  1. Calls `Ratings Data Service` to fetch movie IDs and ratings for the specified user.
  2. For each movie ID, calls `Movie Info Service` to fetch the movie details.
  3. Combines the movie description and rating and returns the aggregated result.
- **Endpoint(s)**:
  - `/catalog/{userId}`: Returns a catalog of movies (movieId, description, rating) rated by the user.

---

## 4. **Discovery Server (Eureka Server)**
- **Description**: Acts as a service registry to enable service discovery and communication between microservices.
- **Role**:
  - Registers all services (`Movie Info Service`, `Ratings Data Service`, `Movie Catalog Service`, and `API Gateway`).
  - Allows services to discover each other dynamically without hardcoding service locations.

---

## 5. **API Gateway**
- **Description**: Provides a single entry point for client requests and routes them to the appropriate microservices.
- **Role**:
  - Routes `/catalog/{userId}` to the `Movie Catalog Service`.
  - Routes `/movies/{movieId}` to the `Movie Info Service`.
  - Routes `/ratings/{userId}` to the `Ratings Data Service`.

---

## Project Workflow
1. **Client Request**:
   - A client sends a request to the `API Gateway`.

2. **Routing via API Gateway**:
   - The `API Gateway` routes the request to the appropriate microservice.

3. **Service Interaction**:
   - For `/catalog/{userId}`:
     1. The `Movie Catalog Service` queries the `Ratings Data Service` for movies rated by the user.
     2. It then queries the `Movie Info Service` to get details for each movie.
     3. Combines the data and returns a response.

4. **Service Discovery**:
   - All microservices register themselves with the `Discovery Server`.
   - Each service dynamically discovers the endpoints of other services via the `Discovery Server`.

---

## Running the Project

### Prerequisites
- Java 17+
- Spring Boot
- H2 Database (in-memory)
- Maven
- Docker (optional for containerization)

### Steps to Run
1. **Start the Eureka Discovery Server**:
   - Navigate to the `discovery-server` directory.
   - Run `mvn spring-boot:run`.

2. **Start the Other Services**:
   - Navigate to each service directory (`movie-info-service`, `ratings-data-service`, `movie-catalog-service`, `api-gateway`).
   - Run `mvn spring-boot:run` in each directory.

3. **Access the Services**:
   - `Discovery Server`: [http://localhost:8761](http://localhost:8761)
   - `API Gateway`: [http://localhost:8080](http://localhost:8080)
   - Example Request:
     - `GET /catalog/{userId}`: Returns the catalog for a user.

---

## Example Response
For `GET /catalog/U001`, the response might look like this:
```json
[
  {
    "movieId": "M001",
    "description": "A thrilling adventure movie with unexpected twists",
    "rating": 4
  },
  {
    "movieId": "M002",
    "description": "An inspiring drama with a heartfelt story",
    "rating": 5
  }
]
```

---

## Directory Structure
```
project-root/
  |-- discovery-server/
  |-- movie-info-service/
  |-- ratings-data-service/
  |-- movie-catalog-service/
  |-- api-gateway/
  |-- README.md
```

---

## Notes
- All services are registered and discovered dynamically using Eureka.
- H2 Database is used for storing application data during runtime.
- API Gateway ensures a unified entry point for clients, improving scalability and security.

