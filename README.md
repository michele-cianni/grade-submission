# Spring Boot Mini Project

This project was developed during the [The Complete Spring Boot Development Bootcamp](https://www.udemy.com/course/the-complete-spring-boot-development-bootcamp/) course on Udemy. It is a simple yet functional application that demonstrates the basics of Spring Boot, such as creating REST APIs, integrating with a database, and using modern development tools.

---

## Features

- Creation of RESTful APIs with Spring Boot
- Integration with a database (MySQL or H2 Database)
- Use of JPA for entity management
- Configuration of a development environment with Spring Boot
- Examples of unit testing for services

---

## Installation and Configuration

To run the project locally:

1. Clone the repository:
   ```bash
   git clone https://github.com/michele-cianni/grade-submission.git
   ```
2. Open the project in a Spring Boot-compatible IDE (e.g., IntelliJ IDEA or Eclipse).
3. Ensure you have Java 11 or later installed.
4. Configure the database in `application.properties` (example for MySQL):
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
5. Start the application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## Usage

The application provides a simple set of RESTful endpoints to manage database entities. Example endpoints:

- **GET** `/grade/all` - Retrieve all grades
- **POST** `/student` - Add a new student
- **PUT** `/course/{id}` - Update an existing course
- **DELETE** `/course/{id}` - Delete a course

You can test the endpoints using tools like Postman or cURL.

---

## Project Structure

- **Controller:** Handles HTTP requests
- **Service:** Business logic
- **Repository:** Database interface with JPA
- **Model:** Defines the entities

---

## License

This project was created for educational purposes. Feel free to use it to learn Spring Boot or as a foundation for other projects.

---

> Project developed during "The Complete Spring Boot Development Bootcamp." If you enjoyed it, consider enrolling in the course to deepen your knowledge!

