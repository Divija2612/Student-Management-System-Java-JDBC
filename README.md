## Student Management System (Java + JDBC + PostgreSQL)
Java Swing based Student Management System using JDBC and PostgreSQL

## Project Description
This is a Java-based Student Management System that performs CRUD operations using JDBC and PostgreSQL. The application features a Swing-based GUI and follows the DAO (Data Access Object) design pattern to maintain clean separation between UI, business logic, and database operations.

---

## Technologies Used
- Java (Core + Swing)
- JDBC (Java Database Connectivity)
- PostgreSQL
- SQL
- DAO Design Pattern

---

## Features
- Add new student records
- View all students in a JTable
- Search student by ID
- Delete student records
- GUI-based application (no console input)
- Secure database access using PreparedStatement

---

## How to Run the Project
## Compile
javac -cp ".;postgresql-42.7.8.jar" *.java
## Run
java -cp ".;postgresql-42.7.8.jar" StudentGUI
