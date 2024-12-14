# E-commerce Backend API

A backend API for an e-commerce platform built using **Spring Boot**. The API manages products, customers, and orders, providing essential endpoints to power an e-commerce application.

---

## Features

- Manage products with CRUD operations.
- Customer management, including registration and order tracking.
- Order processing with timestamps.
- Seamless integration with relational databases using JPA and Hibernate.

---

## Data Models

### 1. **Product**
Represents an item in the store that can be ordered by customers.

| Field        | Type       | Description                  |
|--------------|------------|------------------------------|
| `productId`  | `Long`     | Unique identifier for the product (auto-generated). |
| `name`       | `String`   | Name of the product.         |
| `description`| `String`   | Description of the product.  |
| `price`      | `Integer`  | Price of the product.        |
| `storeName`  | `String`   | Name of the store selling the product. |

---

### 2. **Customer**
Represents a customer who can place orders on the platform.

| Field        | Type       | Description                  |
|--------------|------------|------------------------------|
| `customerId` | `Long`     | Unique identifier for the customer (auto-generated). |
| `name`       | `String`   | Name of the customer.        |
| `address`    | `String`   | Customer's shipping address. |
| `password`   | `String`   | Customer's password for authentication. |
| `order`      | `Set<Order>` | List of orders placed by the customer (bidirectional mapping). |

> **Note:**
> - To avoid infinite recursion during JSON serialization, the `@JsonIgnore` annotation is used in the `order` field.
> - Avoid using `toString()` on bidirectional entities to prevent stack overflow errors.

---

### 3. **Order**
Represents a purchase made by a customer.

| Field        | Type            | Description                  |
|--------------|-----------------|------------------------------|
| `orderId`    | `Long`          | Unique identifier for the order (auto-generated). |
| `customerId` | `Customer`      | Reference to the customer who placed the order. |
| `productId`  | `Product`       | Reference to the purchased product. |
| `dateCreated`| `LocalDateTime` | Timestamp when the order was created (auto-generated). |

---

### Endpoints

This section details the available API endpoints for managing customers in the e-commerce platform.

### Customer Controller
#### 1. **Get Customer**

- **Endpoint:** `api/customer?customerId=6`
- **Method:** `GET`
- **Description:** Retrieves the details of a customer by their unique `customerId`. It also returns the IDs of the orders placed by the customer.
- **Query Parameters:**
    - `customerId` (Long): The unique identifier of the customer.
- **Response:**
    - **Success (200 OK):** Returns a `CustomerVo` object containing the customer's information along with a list of `orderIds`.
    - **Failure (400 NOT FOUND):** Returns a failure message if the customer does not exist.
  
#### 2. **Add Customer**

- **Endpoint:** `api/customer`
- **Method:** `POST`
- **Request Body:**  
```json
{
  "name": "Thomas",
  "password": "Test123",
  "address": "Tampines st 342 blk 123"
}
```
- **Description:** Creates a new customer by accepting the customer's `Name`, `Password`, and `Address` in the request body.
- **Response:**
    - **Success (200 OK):** Returns a `CustomerVo` object containing the customer's information along with a list of `orderIds`.

#### 3. **Delete Customer** (TO be added)

- **Endpoint:** `api/customer`
- **Method:** `POST`
- **Request Body:**

- **Description:** Creates a new customer by accepting the customer's `Name`, `Password`, and `Address` in the request body.
- **Response:**
    - **Success (200 OK):** Returns a `CustomerVo` object containing the customer's information along with a list of `orderIds`.
    - **Failure (404 Not Found):** Returns a failure message if the customer does not exist.

### Product Controller

#### 1. **Get Product**

- **Endpoint:** `api/product?productId=1`
- **Method:** `GET`
- **Description:** Retrieves the details of a product by their unique `productId`.
    - `productId` (Long): The unique identifier of the product.
- **Response:**
    - **Success (200 OK):** Returns a `ProductVo` object containing the product's information.
    - **Failure (404 NOT FOUND):** Returns a failure message if the product does not exist.

#### 2. **Get all products**

- **Endpoint:** `api/product/all`
- **Method:** `GET`
- **Description:** Retrieves the details of all prodects.
- **Response:**
    - **Success (200 OK):** Returns a list of `ProductVo` object containing all the products in the database
  
## Tech Stack

- **Backend Framework:** Spring Boot
- **ORM:** Hibernate (JPA)
- **Database:** MySQL/PostgreSQL (can be configured)
- **Serialization:** Jackson (for JSON processing)
- **Build Tool:** Maven

---

## Installation

### Prerequisites
- Java 17+
- Maven
- MySQL/PostgreSQL database

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ecommerce-backend.git
   cd ecommerce-backend
