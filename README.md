# Advanced Retail Super Shop

A **Spring Boot REST API** for managing a retail super shop including product management, customer management, and order processing with automatic stock control.

## 🚀 Features

* Product Management
* Customer Registration
* Order Processing
* Automatic Stock Reduction
* RESTful API Architecture

## 🏗 Architecture

Layered Spring Boot architecture:

Entity → Repository → Service → Controller

## 🛠 Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Lombok
* MySQL

## 📡 API Endpoints

| Method | Endpoint         | Description   |
| ------ | ---------------- | ------------- |
| POST   | `/shop/product`  | Add product   |
| POST   | `/shop/customer` | Add customer  |
| POST   | `/shop/order`    | Create order  |
| GET    | `/shop/products` | List products |
| GET    | `/shop/orders`   | List orders   |

## ▶ Run Project

1. Clone the repository
2. Configure MySQL database
3. Run the Spring Boot application

```
mvn spring-boot:run
```

## 📌 Author

Jisan – Data Science & Software Engineering Student
