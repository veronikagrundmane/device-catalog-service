[README.md](https://github.com/user-attachments/files/26748858/README.md)
# 🛒 Smart Device Store – Microservices

This project is a microservices-based system for managing devices and customer orders.  
It demonstrates a typical architecture using Spring Boot + API Gateway.

## 🏗️ Architecture

Client → API Gateway (8080)
  ├── /api/devices/** → Device Service (8081)
  └── /api/orders/**  → Order Service (8082)

## 🌐 API Gateway

Base URL:
http://localhost:8080

All requests must go through the gateway.

## ⚙️ Ports

- Gateway: 8080
- Device Service: 8081
- Order Service: 8082

## 📱 Device API

GET /api/devices  
GET /api/devices/{id}  
POST /api/devices  

## 📦 Order API

POST /api/orders  
GET /api/orders/{id}  
GET /api/orders/customer/{customerId}  
PUT /api/orders/{id}/status  
DELETE /api/orders/{id}  
GET /api/orders/{orderId}/items  

## 🧪 Demo Flow

1. Create device  
2. Create order  
3. Get order  
4. Update status  
5. Cancel order  
