# Movie Database RESTful API

This project is a RESTful API designed for managing a movie database. It allows users to perform CRUD operations on movies and genres. The API is built using **Spring Boot** and documented with **Swagger**.

---

## Features

- Manage genres (add, update, delete, and retrieve all genres).
- Manage movies (add, update, delete, retrieve movies, and filter movies by genre or title).
- Interactive API documentation with Swagger.
- Clear structure and maintainable code.

---

## Technologies Used

- **Java**
- **Spring Boot**
- **Hibernate/JPA**
- **Swagger**
- **ModelMapper**
- **H2 Database** (for development/testing purposes)

---

API Endpoints
Genre Endpoints
Add Genre

URL: /addGenre
Method: POST
Request Body:
json
Copy code
{
  "name": "Action"
}
Response:
Genre added successfully
Genre already exists

---

