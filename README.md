# 🎟️ Event App - CRUD Básico de Eventos en Java + MySQL

**Event App** es una aplicación Java sencilla que permite gestionar eventos (crear y listar) utilizando una base de datos **MySQL**.  
Este repositorio es un **fork** del proyecto original y ha sido **comentado línea a línea** con fines **educativos** para facilitar su estudio y comprensión.

---

## 🚀 Propósito del proyecto

> Este proyecto fue clonado y adaptado con fines de **aprendizaje**.  
> Todo el código ha sido **cuidadosamente comentado** para que estudiantes de Java comprendan cómo funciona una aplicación real:
>
> - Uso de **JDBC** para conectarse a una base de datos.
> - Aplicación del patrón **MVC** para una mejor arquitectura.
> - Ejecución desde consola para mantenerlo simple y enfocado en la lógica.

---

## 🏗️ **Estructura de carpetas recomendada**

```bash
src/
└── main/
    └── java/
        └── org/
            └── factoria/
                ├── Main.java
                ├── config/
                │   └── DBManager.java
                ├── model/
                │   └── Event.java
                ├── repository/
                │   └── EventRepository.java
                ├── controller/
                │   └── EventController.java
                └── view/
                    └── EventView.java
```
## Estructura del proyecto

El proyecto sigue una arquitectura por capas basada en el patrón MVC:

| Capa                  | Descripción                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| **Modelo**            | Representa la estructura de los datos. Ej: `Event.java`                    |
| **Vista**             | Interactúa con el usuario a través de la consola. Ej: `EventView.java`     |
| **Controlador**       | Gestiona la lógica de negocio y conecta la vista con los datos. Ej: `EventController.java` |
| **Repositorio**       | Se encarga de las operaciones con la base de datos usando JDBC. Ej: `EventRepository.java` |
| **Gestor de conexión**| Controla la conexión con la base de datos MySQL. Ej: `DBManager.java`      |

---

## **¿Cómo funciona el flujo completo?**

1. **El usuario ejecuta la app** (`Main.java`).
2. **Se abre la conexión** a la base de datos.
3. **La vista** (`EventView`) pide datos al usuario o muestra eventos.
4. **La vista llama al controlador** (`EventController`).
5. **El controlador llama al repositorio** (`EventRepository`).
6. **El repositorio ejecuta SQL** para guardar o consultar datos.
7. **Los resultados vuelven** por el mismo camino hasta la vista.
8. **Se cierra la conexión** al terminar.

## PASO A PASO DETALLADO PARA CREAR LA APP

### 1. **Agrega la dependencia de MySQL en tu pom.xml**

- **¿Qué hace?** Permite usar el conector JDBC de MySQL.
- **Ejemplo:**

    ```xml
    <!-- pom.xml -->
        <dependencies>
            <dependency>
                <groupId>com.mysql</groupId>
                <artifactId>mysql-connector-j</artifactId>
                <version>9.3.0</version>
            </dependency>
        </dependencies>
    ```
---

### 2. **Configura tu base de datos MySQL en Workbench**

- **En MySQL Workbench crea la base de datos y la tabla**:

    ```sql
    CREATE DATABASE event_app;
    USE event_app;
    
    CREATE TABLE events (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(100),
      description TEXT,
      price DOUBLE
    );
    
    ```


---

### 3. 📁 **`DBManager.java`** (Configuración / Conexión)

- **Qué es:** Clase encargada de gestionar la conexión con la base de datos.
- **Función:**
    - Abrir la conexión a la base de datos MySQL (usando JDBC).
    - Proveer esa conexión a otras clases cuando la necesitan.
    - Cerrar la conexión cuando la aplicación termina.
- **Por qué es importante:** Centraliza la conexión y evita abrir múltiples conexiones, optimizando recurso

---

### 4. 📁 **`Event.java`** (Modelo)

- **Qué es:** Clase que representa el modelo de datos del sistema, en este caso un evento.
- **Función:**
    - Define los atributos que tiene un evento (`id`, `name`, `description`, `price`).
    - Contiene los métodos getters y setters para acceder/modificar esos datos.
    - Sirve como objeto para transportar datos dentro de la aplicación (DTO/POJO).
- **Por qué es importante:** Define la estructura de los datos y es la base para manipular la información.

---

### 5. 📁 **`EventRepository.java`** (Repositorio / Acceso a datos)

- **Qué es:** Clase encargada de manejar todas las operaciones de base de datos relacionadas con los eventos.
- **Función:**
    - Ejecutar sentencias SQL para guardar, buscar, actualizar o eliminar eventos.
    - Convertir resultados SQL en objetos `Event` y viceversa.
- **Por qué es importante:** Aísla la lógica de acceso a la base, manteniendo el resto de la app independiente de SQL/JDBC.

---

### 6. 📁 **`EventController.java`** (Controlador / Lógica de negocio)

- **Qué es:** Clase que actúa como intermediario entre la vista y el repositorio.
- **Función:**
    - Recibe peticiones de la vista para realizar acciones (guardar, listar).
    - Llama al repositorio para ejecutar esas acciones.
    - Devuelve resultados a la vista.
- **Por qué es importante:** Separa la lógica de negocio de la presentación y el acceso a datos, facilitando mantenimiento y escalabilidad.

---

### 7. 📁 **`EventView.java`** (Vista / Interfaz de usuario)

- **Qué es:** Clase que se comunica con el usuario, en este caso por consola.
- **Función:**
    - Muestra menús y pide datos al usuario.
    - Llama al controlador para realizar acciones con esos datos.
    - Muestra resultados o mensajes por consola.
- **Por qué es importante:** Encapsula la interacción con el usuario, permitiendo cambiar la interfaz sin afectar la lógica.

---

### 8. 📁 **`Main.java`** (Clase principal / Punto de entrada)

- **Qué es:** Clase que inicia la ejecución de la aplicación.
- **Función:**
    - Abre la conexión con la base de datos.
    - Instancia la vista para interactuar con el usuario.
    - Ejecuta las funciones deseadas (guardar/listar eventos).
    - Cierra la conexión al terminar.
- **Por qué es importante:** Controla el ciclo de vida de la aplicación y el flujo principal.

---

### 9. **Notas finales**

- **Recuerda crear la base de datos y la tabla** en MySQL antes de ejecutar la app.
- **Usa try/catch para manejar errores** de SQL.
- **Configura las variables de entorno** con las credenciales de tu usuario MySQL.