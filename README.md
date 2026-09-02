# StockSphere - Inventory & Warehouse Management System

A backend inventory management system built with Java and Spring Boot for managing products, companies, warehouses, inventory levels, suppliers, and low-stock alerts.

## 📋 Overview

StockSphere is an inventory management platform designed to help businesses manage products and monitor stock across multiple warehouses.

The application provides RESTful APIs for product creation, inventory tracking, warehouse management, supplier information, and low-stock monitoring.

The project follows a layered architecture using Spring Boot, Spring Data JPA, Hibernate, and a relational database.

## 🎯 Features

* Product management
* Unique SKU validation
* Multi-company inventory management
* Warehouse management
* Inventory tracking across warehouses
* Supplier management
* Low-stock alerts
* Stock threshold monitoring
* Product bundle relationships
* Request validation
* Exception handling
* Transaction management
* RESTful API design
* Sample development data

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* Hibernate
* Bean Validation
* Lombok
* Maven

### Database

* H2 Database for development
* PostgreSQL configuration for production

### Tools

* IntelliJ IDEA
* Git
* GitHub
* Postman

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
   |
   v
REST Controller
   |
   v
Service Layer
   |
   v
Repository Layer
   |
   v
JPA / Hibernate
   |
   v
Database
```

### Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── stocksphere/
│   │           ├── StockSphereApplication.java
│   │           │
│   │           ├── entity/
│   │           │   ├── Product.java
│   │           │   ├── Company.java
│   │           │   ├── Warehouse.java
│   │           │   ├── Inventory.java
│   │           │   └── Supplier.java
│   │           │
│   │           ├── dto/
│   │           │   ├── ProductRequestDto.java
│   │           │   ├── LowStockAlertDto.java
│   │           │   └── SupplierDto.java
│   │           │
│   │           ├── repository/
│   │           │   ├── ProductRepository.java
│   │           │   ├── CompanyRepository.java
│   │           │   ├── WarehouseRepository.java
│   │           │   ├── InventoryRepository.java
│   │           │   └── SupplierRepository.java
│   │           │
│   │           ├── service/
│   │           │   ├── ProductService.java
│   │           │   └── AlertService.java
│   │           │
│   │           ├── controller/
│   │           │   ├── ProductController.java
│   │           │   └── AlertController.java
│   │           │
│   │           └── config/
│   │               └── DataInitializer.java
│   │
│   └── resources/
│       └── application.yml
│
└── test/
```

> Note: The package and class names above should match the actual source code after the StockSphere renaming is completed.

## 🚀 Getting Started

### Prerequisites

Make sure the following are installed:

* Java 17 or higher
* Maven 3.6 or higher
* Git
* IntelliJ IDEA, Eclipse, or VS Code
* Postman (recommended for API testing)

### Clone the Repository

```bash
git clone https://github.com/vaishnavimankar795-dot/stocksphere-inventory-management.git
```

Move into the project directory:

```bash
cd stocksphere-inventory-management
```

### Build the Project

```bash
mvn clean compile
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## 🗄️ Database Configuration

### Development - H2

The application can use an in-memory H2 database during development.

Example configuration:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:stocksphere
    driver-class-name: org.h2.Driver
    username: sa
    password: password

  h2:
    console:
      enabled: true

  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

### H2 Console

After starting the application:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL: jdbc:h2:mem:stocksphere
Username: sa
Password: password
```

## 📚 API Documentation

### Base URL

```text
http://localhost:8080/api
```

---

### 1. Create Product

**Endpoint**

```http
POST /api/products
```

Creates a new product and initializes its inventory in the selected warehouse.

### Request

```json
{
  "name": "Wireless Bluetooth Headphones",
  "sku": "WBH-001",
  "price": 99.99,
  "warehouseId": 1,
  "initialQuantity": 50
}
```

### Validation

* Product name cannot be blank
* SKU is required
* SKU must be unique
* Price must be greater than zero
* Warehouse must exist
* Initial quantity cannot be negative

### Example Response

```json
{
  "message": "Product created successfully",
  "product_id": 123
}
```

### Example cURL

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Gaming Laptop",
    "sku": "LAPTOP-GAMING-001",
    "price": 1299.99,
    "warehouseId": 1,
    "initialQuantity": 25
  }'
```

---

### 2. Low-Stock Alerts

**Endpoint**

```http
GET /api/companies/{companyId}/alerts/low-stock
```

Returns products whose current stock is below the configured threshold.

### Example

```bash
curl http://localhost:8080/api/companies/1/alerts/low-stock
```

### Example Response

```json
[
  {
    "productId": 1,
    "productName": "Laptop Computer",
    "sku": "LAPTOP-001",
    "warehouseId": 1,
    "warehouseName": "Main Warehouse",
    "currentStock": 5,
    "threshold": 15,
    "daysUntilStockout": 5,
    "supplier": {
      "id": 1,
      "name": "Global Suppliers Inc",
      "contactEmail": "contact@globalsuppliers.com"
    }
  }
]
```

## 🏢 Multi-Company Support

StockSphere supports multiple companies.

Each company can have:

* Multiple warehouses
* Multiple products
* Separate inventory records
* Associated suppliers

This allows inventory data to be logically separated between different companies.

## 📦 Inventory Management

Inventory is tracked at the warehouse level.

For example:

```text
Company
   |
   ├── Warehouse A
   │      ├── Laptop - 50
   │      └── Mouse  - 100
   │
   └── Warehouse B
          ├── Laptop - 20
          └── Mouse  - 75
```

This allows the same product to have different stock quantities in different warehouses.

## 🚨 Low-Stock Monitoring

Products can have configurable stock thresholds.

For example:

```text
Product: Laptop
Current Stock: 5
Threshold: 15
```

Because the current stock is below the threshold, the product appears in the low-stock alert response.

The alert can include:

* Product information
* SKU
* Warehouse
* Current stock
* Threshold
* Supplier information
* Estimated days until stockout

## 👨‍💼 Supplier Management

Products can be associated with suppliers.

Supplier information includes:

* Supplier ID
* Supplier name
* Contact information

This information can be included in low-stock alerts to help businesses identify potential suppliers for replenishment.

## 🧩 Product Relationships

The application supports relationships between products, including product bundles.

The entity model uses JPA relationships such as:

```text
@OneToMany
@ManyToOne
@ManyToMany
```

These relationships allow the application to represent real-world inventory structures.

## ✅ Validation & Error Handling

The application validates incoming API requests using Bean Validation.

Examples include:

* Required fields
* Blank values
* Positive prices
* Non-negative quantities
* Existing warehouse validation
* Unique SKU validation

The API uses appropriate HTTP status codes for successful and failed requests.

Examples:

```text
200 OK
201 CREATED
400 BAD REQUEST
404 NOT FOUND
```

## 🔄 Transaction Management

Operations that modify multiple related database records can use transactional processing to maintain data consistency.

For example:

```text
Create Product
      |
      ├── Save Product
      |
      └── Create Initial Inventory
```

Both operations should succeed together or be rolled back when an error occurs.

## 🧪 Testing

Testing should cover:

### Unit Testing

* Service layer logic
* Validation rules
* Inventory calculations
* Alert generation

### Repository Testing

* Product queries
* Inventory queries
* Warehouse relationships

### API Testing

* Product creation
* Validation errors
* Low-stock alerts
* Invalid company/warehouse requests

Run tests using:

```bash
mvn test
```

## 📊 Sample Development Data

The development environment contains sample data for testing.

### Company

```text
TechCorp
```

### Warehouses

```text
Main Warehouse
Secondary Warehouse
```

### Products

```text
Laptop Computer
SKU: LAPTOP-001

Wireless Mouse
SKU: MOUSE-001
```

### Supplier

```text
Global Suppliers Inc
```

## 🔐 Security

Authentication and authorization are planned for a future version.

The current version focuses on inventory management, REST API design, database relationships, validation, and business logic.

## 🚀 Future Enhancements

Planned improvements include:

* JWT authentication
* Role-based authorization
* Admin and user roles
* Inventory IN/OUT transaction history
* React.js frontend
* MySQL database support
* Advanced product search
* Pagination and sorting
* CSV/Excel product import
* Purchase order management
* Email low-stock notifications
* Inventory analytics dashboard
* Automated reorder recommendations
* Docker deployment
* Cloud deployment

## 📈 Future Full-Stack Architecture

The planned full-stack version will use:

```text
React.js
    |
    | REST API
    v
Spring Boot
    |
    | Spring Data JPA
    v
MySQL
```

## 🛠️ Development Guidelines

The project follows:

* Layered architecture
* Separation of concerns
* RESTful API principles
* DTO-based API communication
* Input validation
* Transaction management
* Meaningful naming conventions
* Clean and maintainable code

## 📌 Git Workflow

Create a feature branch:

```bash
git checkout -b feature/feature-name
```

After making changes:

```bash
git add .
git commit -m "feat: add feature description"
git push origin feature/feature-name
```

## 📄 License

This project is intended as a personal learning and portfolio project.

## 👩‍💻 Author

**Vaishnavi Mankar**

GitHub:

https://github.com/vaishnavimankar795-dot

---

**StockSphere - Inventory & Warehouse Management System**

Built with Java, Spring Boot, Spring Data JPA and REST APIs.
