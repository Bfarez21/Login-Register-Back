# Backend - Autenticación con JWT (Spring Boot + PostgreSQL)

Este proyecto es un backend desarrollado con **Spring Boot** que implementa autenticación y autorización mediante **JSON Web Tokens (JWT)**. El sistema permite registrar usuarios, iniciar sesión y acceder a rutas protegidas solo con un token válido.

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Security
- JWT (jjwt)
- PostgreSQL
- Maven

---

## ⚙️ Configuración del entorno

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/backend-jwt-auth.git
cd backend-jwt-auth
```

### 2. Configurar la base de datos

Crea una base de datos PostgreSQL y modifica el archivo application.properties o application.yml con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jwt_db
spring.datasource.username=postgres
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

### 3. Ejecutar la aplicación

Puedes ejecutar el proyecto desde tu IDE o con Maven:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Ejemplos de peticiones

### 🔐 Registro de usuario (POST /auth/register)

**Request:**
```json
{
  "name": "name",
  "apellido": "apellido",
  "dni": "0987654123",
  "username": "tuUser@gmail.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicnlhbkBnbWFpbC5jb20iLCJleHAiOjE3M..."
}
```
Usa este token para acceder a recursos protegidos con el siguiente encabezado:
```
Authorization: Bearer <token>
```
### 🔑 Inicio de sesión (POST /auth/signin)

**Request:**
```json
{
  "username": "tuUser@gmail.com",
  "password": "123456"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicnlhbkBnbWFpbC5jb20iLCJleHAiOjE3M...",
  "usuario": {
    "idusuario": 1
  }
}
```

### 🔒 Acceso a recurso protegido (GET /usuarios)

**Headers:**
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

**Response:**
```json
[
  {
    "idusuario": 1,
    "name": "name",
    "apellido": "apellido",
    "dni": "0987654123",
    "username": "tuUser@gmail.com",
    "enabled": true,
    "authorities": [],
    "accountNonLocked": true,
    "accountNonExpired": true,
    "credentialsNonExpired": true
  }
]
```

---

## 🛡️ Seguridad

El backend utiliza filtros personalizados para:
- Validar el JWT en cada solicitud protegida.
- Registrar nuevos usuarios con contraseñas encriptadas (BCrypt).
- Acceder a endpoints solo con un token válido.

---

## 📂 Estructura de carpetas

```
src/
├── config/         # Configuración de seguridad
├── controller/     # Controladores REST
├── dto/            # Objetos de transferencia de datos
|── jwt/            # Clases auxiliares (JWT)
├── model/          # Entidades JPA
├── repository/     # Interfaces para acceso a datos
├── service/        # Lógica de negocio
```

---

## 📄 Licencia

Este proyecto es de uso libre para fines educativos o comerciales. Puedes modificarlo y adaptarlo según tus necesidades.

---

## Autor

Desarrollado por Bryan Fárez