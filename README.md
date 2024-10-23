# API de Franquicias

Este es un proyecto de API REST que gestiona franquicias, sucursales y productos. La API permite realizar operaciones CRUD sobre estos recursos.

## Tecnologías

- **Java 17**
- **Spring Boot**
- **MongoDB**
- **Postman** (para probar los endpoints)

- **Nombre de la bade de datos = franquiciaDB

### Probar los Endpoints
  1. **Agregar una nueva franquicia**
   - **Método:** `POST`
   - **URL:** `/franquicias`
   - **Request Body:**
     ```json
     {
       "nombre": "Nombre de la franquicia",
       "sucursales": []
     }
     ```
   - **Respuesta:**
     ```json
     {
       "id": "ID generado",
       "nombre": "Nombre de la franquicia",
       "sucursales": []
     }
     ```

    2. **Obtener una franquicia por ID**
    - **Método:** `GET`
    - **URL:** `/franquicias/{id}`
    - **Respuesta:**
        ```json
        {
        "id": "ID",
        "nombre": "Nombre de la franquicia",
        "sucursales": []
        }
        ```

    3. **Obtener todas las franquicias**
    - **Método:** `GET`
    - **URL:** `/franquicias`
    - **Respuesta:**
        ```json
        [
        {
            "id": "ID",
            "nombre": "Nombre de la franquicia",
            "sucursales": []
        }
        ]
        ```

    4. **Modificar el nombre de la franquicia**
    - **Método:** `PATCH`
    - **URL:** `/franquicias/{franquiciaId}/nombre`
    - **Request Params:**
        - `nuevoNombre`: Nuevo nombre de la franquicia.
    - **Respuesta:**
        ```json
        {
        "id": "ID",
        "nombre": "Nuevo nombre de la franquicia",
        "sucursales": []
        }
        ```

    ### Sucursales y Productos

    1. **Agregar una nueva sucursal a una franquicia**
    - **Método:** `POST`
    - **URL:** `/franquicias/{franquiciaId}/sucursales`
    - **Request Body:**
        ```json
        {
        "nombre": "Nombre de la sucursal",
        "productos": []
        }
        ```
    - **Respuesta:**
        ```json
        {
        "id": "ID",
        "nombre": "Nombre de la sucursal",
        "productos": []
        }
        ```

    2. **Agregar un nuevo producto a una sucursal**
    - **Método:** `POST`
    - **URL:** `/franquicias/{franquiciaId}/sucursales/{sucursalId}/productos`
    - **Request Body:**
        ```json
        {
        "nombre": "Nombre del producto",
        "stock": 100
        }
        ```
    - **Respuesta:**
        ```json
        {
        "id": "ID",
        "nombre": "Nombre del producto",
        "stock": 100
        }
        ```

    3. **Eliminar un producto en una sucursal**
    - **Método:** `DELETE`
    - **URL:** `/franquicias/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}`
    - **Respuesta:**
        ```json
        {
        "message": "Producto eliminado"
        }
        ```

    4. **Modificar el stock de un producto**
    - **Método:** `PATCH`
    - **URL:** `/franquicias/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock`
    - **Request Params:**
        - `nuevoStock`: Nuevo stock del producto.
    - **Respuesta:**
        ```json
        {
        "id": "ID",
        "nombre": "Nombre del producto",
        "stock": nuevoStock
        }
        ```

    5. **Modificar un producto en una sucursal (nombre, stock)**
    - **Método:** `PUT`
    - **URL:** `/franquicias/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}`
    - **Request Body:**
        ```json
        {
        "nombre": "Nuevo nombre del producto",
        "stock": 150
        }
        ```
    - **Respuesta:**
        ```json
        {
        "id": "ID",
        "nombre": "Nuevo nombre del producto",
        "stock": 150
        }
        ```

    6. **Obtener el producto con más stock por sucursal de una franquicia**
    - **Método:** `GET`
    - **URL:** `/franquicias/{franquiciaId}/productos-max-stock`
    - **Respuesta:**
        ```json
        [
        {
            "id": "ID",
            "nombre": "Nombre del producto",
            "stock": 200
        }
        ]
        ```



### Notas
Asegúrate de que MongoDB esté en ejecución antes de iniciar la aplicación.
Puedes usar herramientas como Postman para probar los endpoints de la API.


### Prerequisitos

- Tener instalado Java 17.
- Tener MongoDB instalado y en ejecución.

### Clonar el Repositorio

```bash
git clone https://github.com/JosimarZa/ApiFranquicia.git
cd ApiFranquicia
