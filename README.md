# Smart Device Store Microservices

Smart Device Store is a Spring Boot microservices sample that manages:

- Device catalog data
- Customer orders containing one or more catalog devices
- API routing through a central gateway

## 1. Project Overview

The repository contains three services:

- `api-gateway` (Spring Cloud Gateway, WebFlux, Maven)
- `device-catalog-service` (Spring Boot REST + JPA + H2, Gradle)
- `order-service` (Spring Boot REST + JPA + H2 + RestClient integration, Gradle)

### Runtime ports

- API Gateway: `8080`
- Device Catalog Service: `8081`
- Order Service: `8082`

### Request flow

```text
Client
  -> API Gateway (:8080)
       -> /api/devices/** -> Device Catalog Service (:8081)
       -> /api/orders/**  -> Order Service (:8082)
```

Gateway routing is defined in `api-gateway/src/main/resources/application.yml`.

## 2. Repository Structure

```text
.
|- api-gateway/
|  |- pom.xml
|  `- src/main/resources/application.yml
|- device-catalog-service/
|  |- build.gradle
|  `- src/main/java/com/smartdevicestore/devicecatalog/
|     |- application/service/DeviceApplicationService.java
|     |- domain/model/Device.java
|     `- web/
|        |- controller/DeviceController.java
|        `- dto/{CreateDeviceRequest,DeviceResponse}.java
`- order-service/
   |- build.gradle
   `- src/main/java/com/smartdevicestore/orderservice/
      |- application/service/OrderService.java
      |- domain/model/{Order,OrderItem,OrderStatus}.java
      |- infrastructure/client/{DeviceServiceClient,DeviceInfo}.java
      `- web/
         |- controller/OrderController.java
         |- dto/{OrderRequest,OrderResponse,OrderItemRequest,OrderItemResponse}.java
         `- exception/GlobalExceptionHandler.java
```

## 3. How The Services Work

## 3.1 Device Catalog Service

- Exposes `/api/devices` endpoints
- Stores devices in in-memory H2 database (`jdbc:h2:mem:devicesdb`)
- Creates and reads `Device` entities through an application service and repository abstraction

Main behavior:

- `GET /api/devices` returns all devices
- `GET /api/devices/{id}` returns a single device or `404`
- `POST /api/devices` creates a new device

## 3.2 Order Service

- Exposes `/api/orders` endpoints
- Stores orders and order items in in-memory H2 database (`jdbc:h2:mem:orderdb`)
- Calls Device Catalog Service to validate each ordered `deviceId` and read current device name/price

Main behavior:

- `POST /api/orders` validates item list and each device ID before persisting order
- Order total is calculated as: `sum(price * quantity)`
- Order status lifecycle values:
  - `PENDING`
  - `CONFIRMED`
  - `PROCESSING`
  - `SHIPPED`
  - `DELIVERED`
  - `CANCELLED`

Error handling in `order-service` is centralized via `GlobalExceptionHandler`:

- `OrderNotFoundException` -> `404`
- `DeviceNotFoundException` -> `400`
- `IllegalArgumentException` -> `400`

## 3.3 API Gateway

The gateway forwards incoming requests by path:

- `/api/devices/**` -> `http://localhost:8081`
- `/api/orders/**` -> `http://localhost:8082`

Use `http://localhost:8080` as the public base URL for all API calls.

## 4. API Reference

All examples below use the gateway URL:

`http://localhost:8080`

## 4.1 Device API

### 4.1.1 List Devices

- Method: `GET`
- Path: `/api/devices`

Response `200 OK`

```json
[
  {
    "id": 1,
    "name": "iPhone 15",
    "type": "PHONE",
    "brand": "Apple",
    "price": 999.99
  }
]
```

### 4.1.2 Get Device By ID

- Method: `GET`
- Path: `/api/devices/{id}`

Response `200 OK`

```json
{
  "id": 1,
  "name": "iPhone 15",
  "type": "PHONE",
  "brand": "Apple",
  "price": 999.99
}
```

Response `404 Not Found`

- Empty body (controller returns `ResponseEntity.notFound().build()`)

### 4.1.3 Create Device

- Method: `POST`
- Path: `/api/devices`

Request payload

```json
{
  "name": "Galaxy S24",
  "type": "PHONE",
  "brand": "Samsung",
  "price": 899.99
}
```

Response `200 OK`

```json
{
  "id": 2,
  "name": "Galaxy S24",
  "type": "PHONE",
  "brand": "Samsung",
  "price": 899.99
}
```

## 4.2 Order API

### 4.2.1 Create Order

- Method: `POST`
- Path: `/api/orders`

Request payload

```json
{
  "customerId": "cust-1001",
  "items": [
    {
      "deviceId": 1,
      "quantity": 2
    },
    {
      "deviceId": 2,
      "quantity": 1
    }
  ]
}
```

Response `201 Created`

```json
{
  "id": 1,
  "customerId": "cust-1001",
  "status": "PENDING",
  "totalAmount": 2899.97,
  "createdAt": "2026-04-15T10:00:00",
  "updatedAt": "2026-04-15T10:00:00",
  "items": [
    {
      "id": 1,
      "deviceId": 1,
      "deviceName": "iPhone 15",
      "price": 999.99,
      "quantity": 2
    },
    {
      "id": 2,
      "deviceId": 2,
      "deviceName": "Galaxy S24",
      "price": 899.99,
      "quantity": 1
    }
  ]
}
```

Response `400 Bad Request` examples

```json
{ "error": "Order must contain at least one item" }
```

```json
{ "error": "Device not found or unavailable: 999" }
```

### 4.2.2 Get Order By ID

- Method: `GET`
- Path: `/api/orders/{id}`

Response `200 OK`: same shape as create-order response.

Response `404 Not Found`

```json
{ "error": "Order not found: 100" }
```

### 4.2.3 Get Orders By Customer

- Method: `GET`
- Path: `/api/orders/customer/{customerId}`

Response `200 OK`

```json
[
  {
    "id": 1,
    "customerId": "cust-1001",
    "status": "PENDING",
    "totalAmount": 2899.97,
    "createdAt": "2026-04-15T10:00:00",
    "updatedAt": "2026-04-15T10:00:00",
    "items": []
  }
]
```

### 4.2.4 Update Order Status

- Method: `PUT`
- Path: `/api/orders/{id}/status`

Request payload

```json
{ "status": "SHIPPED" }
```

Response `200 OK`: full `OrderResponse` with updated `status` and `updatedAt`.

Response `400 Bad Request` (invalid enum value)

```json
{ "error": "No enum constant com.smartdevicestore.orderservice.domain.model.OrderStatus.INVALID" }
```

Response `404 Not Found`

```json
{ "error": "Order not found: 100" }
```

### 4.2.5 Cancel Order

- Method: `DELETE`
- Path: `/api/orders/{id}`

Behavior:

- Marks order as `CANCELLED`
- Returns no body

Response `204 No Content`

Response `404 Not Found`

```json
{ "error": "Order not found: 100" }
```

### 4.2.6 Get Items For One Order

- Method: `GET`
- Path: `/api/orders/{orderId}/items`

Response `200 OK`

```json
[
  {
    "id": 1,
    "deviceId": 1,
    "deviceName": "iPhone 15",
    "price": 999.99,
    "quantity": 2
  }
]
```

Response `404 Not Found`

```json
{ "error": "Order not found: 100" }
```

## 5. Quick Start

Start each service in separate terminals.

### 5.1 API Gateway (Maven)

```powershell
cd api-gateway
./mvnw spring-boot:run
```

### 5.2 Device Catalog Service (Gradle)

```powershell
cd device-catalog-service
./gradlew bootRun
```

### 5.3 Order Service (Gradle)

```powershell
cd order-service
./gradlew bootRun
```

## 6. End-to-End Example Flow

1. Create a device with `POST /api/devices`.
2. Create an order with `POST /api/orders` using created `deviceId`.
3. Fetch order by id: `GET /api/orders/{id}`.
4. Update status: `PUT /api/orders/{id}/status`.
5. List items: `GET /api/orders/{id}/items`.
6. Cancel order: `DELETE /api/orders/{id}`.

## 7. Notes and Current Limitations

- No authentication/authorization is configured.
- Request field validation annotations are not present.
- Databases are in-memory H2 and reset when services restart.
- No API versioning or OpenAPI/Swagger document is currently configured.
