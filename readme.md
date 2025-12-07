# 📊 Demand Forecast System

This project is a **complete product demand forecasting system**, built with a microservices architecture using **Java (Spring Boot)** on the backend, **Python (Machine Learning)** for predictive calculations, **PostgreSQL** as the database, and **Angular** on the frontend.

The system allows:

* Product registration
* Sales history storage
* Automatic sending of sales history to an AI microservice
* Demand prediction return to the main system

---

## 🚀 Technologies Used

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Web
* Spring Cloud OpenFeign
* PostgreSQL
* Lombok

### AI / Machine Learning

* Python 3
* FastAPI or Flask
* Machine Learning libraries (in progress)

### Frontend

* Angular

---

## 🏗️ System Architecture

```
Angular → Spring Boot → Feign → Python (ML) → Spring Boot → Angular
                    ↓
                PostgreSQL
```

✅ Java sends sales data to Python via REST
✅ Python processes the demand forecast using ML
✅ The prediction is returned to Java and displayed on the frontend

---

## 📦 Project Structure

```
demand-forecast/
├── backend/   (Spring Boot)
├── python-ml/ (AI Microservice)
└── frontend/  (Angular)
```

---

## ⚙️ How to Run Locally

### 1️⃣ Create the database in PostgreSQL

```sql
CREATE DATABASE demand_forecast;
```

---

### 2️⃣ Configure application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/demand_forecast
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

### 3️⃣ Run the Backend

```bash
cd backend
mvn spring-boot:run
```

---

### 4️⃣ Run the Python Microservice

```bash
cd python-ml
python main.py
```

---

## 🔗 Main Endpoints

### ✅ Products

**Create product**

```
POST /api/products
```

```json
{
  "name": "Rice 5kg",
  "category": "Food",
  "price": 21.90
}
```

**List products**

```
GET /api/products
```

---

### ✅ Demand Forecast

```
GET /api/forecast/{productId}
```

✅ The system automatically collects the product's sales history and sends it to the AI service.

---

### ✅ Python AI Microservice

```
POST /predict
```

```json
{
  "productId": 1,
  "history": [10, 12, 15, 18, 20, 22, 25]
}
```

**Response:**

```json
{
  "forecast": 28.4
}
```

---

## 🧠 How the AI Works (Current Version)

* Java sends only the **product sales history**
* Python calculates the **next-period demand forecast**
* If the AI service is offline, Java applies a **statistical fallback (average + 10%)**

---

## 📈 Planned Future Improvements

* ✅ Linear Regression
* ✅ ARIMA / Prophet (Time Series Forecasting)
* ✅ Angular Dashboard with Charts
* ✅ Message Queues (RabbitMQ or Kafka)
* ✅ Docker Deployment

---


This project was designed to simulate a **real-world business scenario**, with scalable architecture, service decoupling, API communication, fallback strategies, and future-ready infrastructure for enterprise environments.
