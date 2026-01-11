
# 📦 Order Management – Spring Boot, MongoDB & RabbitMQ

Este proyecto es una aplicación backend desarrollada con **Spring Boot**, que implementa un sistema básico de **gestión de pedidos (Orders)** utilizando **MongoDB** como base de datos y **RabbitMQ** para mensajería basada en eventos.

El objetivo principal es demostrar una arquitectura limpia con separación por capas, uso de DTOs, persistencia en MongoDB y publicación/consumo de eventos mediante RabbitMQ.

---

## 🚀 Tecnologías utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Web**
* **Spring Data MongoDB**
* **Spring AMQP (RabbitMQ)**
* **MongoDB**
* **RabbitMQ**
* **Lombok**
* **Jackson (JSON)**
* **Docker / Docker Compose** (opcional)

---

## 🧱 Arquitectura del proyecto

El proyecto sigue una arquitectura por capas:

```
src/main/java/com/practica_n1/practica
│
├── controller      → Controladores REST (API)
├── domain          → Entidades / DTOs (Order)
├── repository      → Acceso a datos (MongoRepository)
├── service         → Lógica de negocio
├── messaging       → Publicadores y consumidores RabbitMQ
├── config          → Configuraciones (RabbitMQ)
└── util            → Servicios auxiliares (JSON)
```

---

## 📄 Modelo principal

### Order

```java
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String product;
    private Integer quantity;
}
```

* El `id` se genera automáticamente por MongoDB.
* La clase funciona tanto como **DTO** como **documento de base de datos**.

---

## 🔗 Endpoints disponibles

### Crear pedido

```http
POST /
```

**Body (JSON):**

```json
{
  "product": "Pizza",
  "quantity": 2
}
```

---

### Listar pedidos

```http
GET /
```

---

### Eliminar pedido

```http
DELETE /{id}
```

---

## 🗄️ Base de datos (MongoDB)

La aplicación se conecta a MongoDB mediante `application.yml`.

Ejemplo de configuración:

```yaml
spring:
  data:
    mongodb:
      connection-string: mongodb://root:root@localhost:27017/practica?authSource=admin
```

MongoDB se encarga automáticamente de:

* Crear la base de datos
* Crear la colección `orders`
* Generar el `id` del documento

---

## 📨 RabbitMQ (Mensajería)

El sistema publica eventos cuando ocurre una acción sobre los pedidos.

### Eventos implementados

* **pedido.created** → Cuando se crea un pedido
* **pedido.deleted** → Cuando se elimina un pedido

### Flujo

1. Se guarda el pedido en MongoDB
2. Se convierte el objeto `Order` a JSON
3. Se publica el mensaje en RabbitMQ
4. El consumidor (`@RabbitListener`) recibe el mensaje y lo procesa

---

## 🔄 Conversión JSON

Se utiliza **Jackson** para convertir objetos a JSON y viceversa.

Ejemplo:

```java
String json = jsonService.toJson(order);
Order order = jsonService.fromJson(json, Order.class);
```

Esto evita problemas con el `SimpleMessageConverter` de RabbitMQ.

---

## 🐳 Docker (opcional)

Ejemplo de servicio MongoDB con Docker:

```yaml
mongodb:
  image: mongo:6.0
  container_name: mongodb
  ports:
    - "27017:27017"
  environment:
    MONGO_INITDB_ROOT_USERNAME: root
    MONGO_INITDB_ROOT_PASSWORD: root
```

---

## ✅ Funcionalidades actuales

* ✔ CRUD básico de pedidos
* ✔ Persistencia en MongoDB
* ✔ Publicación de eventos con RabbitMQ
* ✔ Consumo de mensajes
* ✔ Conversión segura a JSON
* ✔ Arquitectura limpia por capas

---

## 📌 Próximas mejoras

* Validaciones con `@Valid`
* Manejo global de errores (`@ControllerAdvice`)
* DTOs separados para API y dominio
* Pruebas unitarias
* Seguridad (Spring Security)
* Versionado de eventos

---

