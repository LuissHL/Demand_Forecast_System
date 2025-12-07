# 📊 Sistema de Previsão de Demanda com IA

Este projeto é um **sistema completo de previsão de demanda de produtos**, utilizando arquitetura em microsserviços com **Java (Spring Boot)** no backend, **Python (Machine Learning)** para os cálculos preditivos, **PostgreSQL** como banco de dados e **Angular** no frontend.

O sistema permite:

* Cadastro de produtos
* Registro de vendas
* Envio do histórico de vendas para um microserviço de IA
* Retorno da previsão de demanda para o próximo período

---

## 🚀 Tecnologias Utilizadas

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Web
* Spring Cloud OpenFeign
* PostgreSQL
* Lombok

### IA / Machine Learning

* Python 3
* FastAPI (ou Flask)
* Bibliotecas de ML (em evolução)

### Frontend

* Angular

---

## 🏗️ Arquitetura do Sistema

```
Angular → Spring Boot → Feign → Python (ML) → Spring → Angular
                 ↓
             PostgreSQL
```

✅ O Java envia os dados de vendas para o Python via REST.
✅ O Python calcula a previsão usando Machine Learning.
✅ O resultado retorna para o Java e é exibido no front.

---

## 📦 Estrutura do Projeto

```
demand-forecast/
├── backend/   (Spring Boot)
├── python-ml/ (Microserviço de IA)
└── frontend/  (Angular)
```

---

## ⚙️ Como Rodar o Projeto Localmente

### 1️⃣ Criar o banco de dados no PostgreSQL

```sql
CREATE DATABASE demand_forecast;
```

### 2️⃣ Configurar o application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/demand_forecast
spring.datasource.username=postgres
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

### 3️⃣ Rodar o Backend

```bash
cd backend
mvn spring-boot:run
```

---

### 4️⃣ Rodar o Microserviço Python

```bash
cd python-ml
python main.py
```

---

## 🔗 Endpoints Principais

### ✅ Produtos

**Criar produto**

```
POST /api/products
```

```json
{
  "name": "Arroz 5kg",
  "category": "Alimentos",
  "price": 21.90
}
```

**Listar produtos**

```
GET /api/products
```

---

### ✅ Previsão de Demanda

```
GET /api/forecast/{productId}
```

✅ O sistema coleta automaticamente o histórico de vendas do produto e envia para a IA.

---

### ✅ Microserviço Python (IA)

```
POST /predict
```

```json
{
  "productId": 1,
  "history": [10, 12, 15, 18, 20, 22, 25]
}
```

**Resposta:**

```json
{
  "forecast": 28.4
}
```

---

## 🧠 Como a IA Funciona Atualmente

* O Java envia apenas **o histórico de vendas do produto**.
* O Python utiliza esse histórico para gerar uma **previsão para o próximo período**.
* Caso o serviço de IA esteja offline, o Java usa um **fallback estatístico (média + 10%)**.

---

## 📈 Próximas Evoluções Planejadas

* ✅ Regressão Linear
* ✅ ARIMA / Prophet (Séries Temporais)
* ✅ Dashboard de gráficos no Angular
* ✅ Filas com RabbitMQ ou Kafka
* ✅ Deploy com Docker

---

## 🧑‍💻 Autor

Projeto desenvolvido por **Luis Henrique** como parte de estudos avançados em:

* Engenharia de Software
* Arquitetura de Sistemas
* Inteligência Artificial aplicada a negócios

---

## 💬 Observação Final

Este projeto foi pensado para simular um cenário **real de mercado**, com arquitetura escalável, separação de responsabilidades, fallback de serviço e possibilidade de crescimento para ambiente corporativo.
