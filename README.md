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

---

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

### 2. **Agrega la dependencia de dotenv en tu pom.xml**

- **¿Qué hace?** Permite usar el .env con las variables de entorno.
- **Ejemplo:**

    ```xml
    <!-- pom.xml -->
        <dependencies>
        <dependency>
            <groupId>io.github.cdimascio</groupId>
            <artifactId>dotenv-java</artifactId>
            <version>3.0.0</version>
        </dependency>
        </dependencies>
    ```
---

### 3. **Crea un archivo llamado .env en la raíz del proyecto (mismo nivel que pom.xml):**

- **¿Qué hace?** Configura las variables de entorno.
- **Ejemplo:**

    ```shell
# Archivo .env
DB_URL=jdbc:mysql://localhost:3306/tu_base_de_datos
DB_USER=root
DB_PASSWORD=tu_contraseña_secreta
DB_PORT=3306```
---

### 4. **Crea/actualiza tu .gitignore:**

- **¿Qué hace?** Configura las variables de entorno.
- **Ejemplo:**

    ```shell
# .gitignore
.env
target/
*.iml
.idea/```
---

### 5. **Configura tu base de datos MySQL en Workbench**

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

### 6. 📁 **`DBManager.java`** (Configuración / Conexión)

- **Qué es:** Clase encargada de gestionar la conexión con la base de datos.
- **Función:**
    - Abrir la conexión a la base de datos MySQL (usando JDBC).
    - Proveer esa conexión a otras clases cuando la necesitan.
    - Cerrar la conexión cuando la aplicación termina.
- **Por qué es importante:** Centraliza la conexión y evita abrir múltiples conexiones, optimizando recurso
- ***No olvides de utilizar las variables de entorno***:
```java
public class DBManager {
    // Cargar variables del archivo .env
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("./")  // directorio donde buscar el .env
            .ignoreIfMissing()  // no fallar si no existe
            .load();

    // Cargar variables de entorno
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    //-------resto del código-------//
}
```

---

### 7. 📁 **`Event.java`** (Modelo)

- **Qué es:** Clase que representa el modelo de datos del sistema, en este caso un evento.
- **Función:**
    - Define los atributos que tiene un evento (`id`, `name`, `description`, `price`).
    - Contiene los métodos getters y setters para acceder/modificar esos datos.
    - Sirve como objeto para transportar datos dentro de la aplicación (DTO/POJO).
- **Por qué es importante:** Define la estructura de los datos y es la base para manipular la información.

---

### 8. 📁 **`EventRepository.java`** (Repositorio / Acceso a datos)

- **Qué es:** Clase encargada de manejar todas las operaciones de base de datos relacionadas con los eventos.
- **Función:**
    - Ejecutar sentencias SQL para guardar, buscar, actualizar o eliminar eventos.
    - Convertir resultados SQL en objetos `Event` y viceversa.
- **Por qué es importante:** Aísla la lógica de acceso a la base, manteniendo el resto de la app independiente de SQL/JDBC.

---

### 9. 📁 **`EventController.java`** (Controlador / Lógica de negocio)

- **Qué es:** Clase que actúa como intermediario entre la vista y el repositorio.
- **Función:**
    - Recibe peticiones de la vista para realizar acciones (guardar, listar).
    - Llama al repositorio para ejecutar esas acciones.
    - Devuelve resultados a la vista.
- **Por qué es importante:** Separa la lógica de negocio de la presentación y el acceso a datos, facilitando mantenimiento y escalabilidad.

---

### 10. 📁 **`EventView.java`** (Vista / Interfaz de usuario)

- **Qué es:** Clase que se comunica con el usuario, en este caso por consola.
- **Función:**
    - Muestra menús y pide datos al usuario.
    - Llama al controlador para realizar acciones con esos datos.
    - Muestra resultados o mensajes por consola.
- **Por qué es importante:** Encapsula la interacción con el usuario, permitiendo cambiar la interfaz sin afectar la lógica.

---

### 11. 📁 **`Main.java`** (Clase principal / Punto de entrada)

- **Qué es:** Clase que inicia la ejecución de la aplicación.
- **Función:**
    - Abre la conexión con la base de datos.
    - Instancia la vista para interactuar con el usuario.
    - Ejecuta las funciones deseadas (guardar/listar eventos).
    - Cierra la conexión al terminar.
- **Por qué es importante:** Controla el ciclo de vida de la aplicación y el flujo principal.

---
## PASO A PASO DETALLADO PARA HACER TESTINGS UNITARIOS
Para garantizar que el controlador funcione correctamente y que las dependencias como el repositorio sean llamadas adecuadamente, se implementan pruebas unitarias utilizando JUnit 5 y Mockito.

### 1. Agregar dependencias en pom.xml
   Agrega estas dependencias en la sección <dependencies> de tu archivo pom.xml:
```xml
    <!-- pom.xml -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>

```
### 2. Crear archivo de prueba EventControllerTest.java
   Ubicación sugerida del archivo:
   src/test/java/org/factoria/controller/EventControllerTest.java
```java
package org.factoria.controller;

import org.factoria.model.Event;
import org.factoria.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// Esta anotación indica que usaremos extensiones de Mockito con JUnit 5
@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    // Simulamos el repositorio para no usar una base de datos real
    @Mock
    private EventRepository eventRepository;

    // Inyectamos el mock del repositorio dentro del controlador
    @InjectMocks
    private EventController eventController;

    // Esta prueba verifica que el controlador llama al repositorio al guardar un evento
    @Test
    void saveEventController_shouldCallRepository() {
        // Arrange: creamos un evento ficticio
        Event event = new Event("Evento 1", "el mejor", 63.2);

        // Act: llamamos al método del controlador
        eventController.saveEventController(event);

        // Assert: verificamos que el método saveEvent del repositorio se llamó exactamente una vez
        verify(eventRepository, times(1)).saveEvent(event);
    }
}

```
### ¿Qué estamos probando?
Que el controlador llama correctamente al método del repositorio.

Que no se ejecuta código SQL real gracias al uso de un mock.

Que la lógica del controlador se prueba de forma aislada del resto del sistema.

### ¿Por qué usamos @Mock y @InjectMocks?
@Mock: crea un objeto simulado de EventRepository, que no hace operaciones reales.

@InjectMocks: crea un EventController y le inyecta el mock automáticamente.

Esto permite probar solo el comportamiento del controlador sin requerir base de datos.

### Buenas prácticas
Usa verify() para asegurarte de que se llaman los métodos correctos.

Usa mocks solo cuando no quieras ejecutar la lógica real (como conexiones a base de datos).

Aísla cada unidad que pruebes: prueba el controlador sin necesidad de probar el repositorio a la vez.

---

### **Notas finales**

- **Recuerda crear la base de datos y la tabla** en MySQL antes de ejecutar la app.
- **Usa try/catch para manejar errores** de SQL.
- **Configura las variables de entorno** con las credenciales de tu usuario MySQL.