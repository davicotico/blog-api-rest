# Blog API REST

## Tecnologías utilizadas

* Lenguaje: Java 17
* Framework: Spring Boot 3
* Base de datos: H2 Database

### Ejecución de la aplicación

Clonar el repositorio

```
git clone https://github.com/davicotico/blog-api-rest.git
```

Entrar a la carpeta del proyecto en `blog-api-rest/apiblog`

```shell
cd blog-api-rest
cd apiblog
```

Ejecutar la aplicación

```shell
./mvnw spring-boot:run
```

# Documentación de la API

## Crear Autor

### Descripción
Esta API permite crear un nuevo autor.

### Endpoint
- **URL:** `http://localhost:8080/api/v1/autor`
- **Método:** `POST`

### Request Body
El cuerpo de la solicitud debe ser un JSON con la siguiente estructura:

```json
{
  "codigoPais": "BO",
  "nombres": "David Tomas",
  "paterno": "Ticona",
  "materno": "Saravia",
  "nacimiento": "1982-01-01",
  "correoElectronico": "davicotico@yandex.com"
}
```

## Crear Blog

### Descripción
Esta API permite crear un nuevo blog

### Endpoint
- **URL:** `http://localhost:8080/api/v1/blog`
- **Método:** `POST`
- **Autenticación:** Ninguna

### Request Body
El cuerpo de la solicitud debe ser un JSON con la siguiente estructura:

```json
{
  "idAutor": 1,
  "idTema": 1,
  "titulo": "Es un titulo prueba",
  "contenido": "test",
  "periodicidad": "DIARIO",
  "permiteComentarios": true
}
```

## Agregar comentario a Blog

### Descripción
Esta API permite agregar un nuevo comentario a un blog

### Endpoint
- **URL:** `http://localhost:8080/api/v1/blog/1/comentario`
- **Método:** `POST`
- **Autenticación:** Ninguna

### Request Body
El cuerpo de la solicitud debe ser un JSON con la siguiente estructura:

```json
{
  "codigoPais": "BO",
  "comentario": "test 123 456 789 10",
  "nombreCompleto": "Wendy Ferreira",
  "correoElectronico": "a123@test.com",
  "puntuacion": 1
}
```

## Actualizar Blog

### Descripción
Esta API permite actualizar un blog existente.

### Endpoint
- **URL:** `http://localhost:8080/api/v1/blog/3`
- **Método:** `PUT`
- **Autenticación:** Ninguna

### Request Body
El cuerpo de la solicitud debe ser un JSON con la siguiente estructura:

```json
{
  "idAutor": 1,
  "idTema": 2,
  "titulo": "MODIFICADO",
  "contenido": "test MODIFICADO",
  "periodicidad": "SEMANAL",
  "permiteComentarios": true
}
```

## Consultar Blog

### Descripción
Esta API permite consultar un blog en particular por su ID numérico

### Endpoint
- **URL:** `http://localhost:8080/api/v1/blog/{ID}`
- **Método:** `GET`
- **Autenticación:** Ninguna

## Consultar comentarios de un blog

### Descripción

Esta API permite consultar la lista de comentarios de un blog en particular por su ID numérico

### Endpoint
- **URL:** `http://localhost:8080/api/v1/blog/{ID}/comentarios`
- **Método:** `GET`
- **Autenticación:** Ninguna

## Consultar el historial de cambios de un blog

### Descripción

Esta API permite consultar todo el historial de cambios de un blog en particular por su ID numérico

### Endpoint
- **URL:** `http://localhost:8080/api/v1/blog/{ID}/historial`
- **Método:** `GET`
- **Autenticación:** Ninguna
