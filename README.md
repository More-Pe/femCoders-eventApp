# 🎟️ Event App - CRUD Básico de Eventos en Java + MySQL

**Event App** es una aplicación Java sencilla que permite gestionar eventos (crear y listar) utilizando una base de datos **MySQL**.  
Este repositorio es un **fork** del proyecto original y ha sido **comentado línea a línea** con fines **educativos** para facilizar su estudio y comprensión.

---

## 🚀 Propósito del proyecto

> Este proyecto fue clonado y adaptado con fines de **aprendizaje**.  
> Todo el código ha sido **cuidadosamente comentado** para que estudiantes de Java comprendan cómo funciona una aplicación real:
>
> - Uso de **JDBC** para conectarse a una base de datos.
> - Aplicación del patrón **MVC** para una mejor arquitectura.
> - Ejecución desde consola para mantenerlo simple y enfocado en la lógica.

---

## 🏗️ Estructura de carpetas recomendada

```bash
src/
├── main/
│   └── java.org.factoria/
│           ├── Main.java
│           ├── config/
│           │   └── DBManager.java
│           ├── model/
│           │   └── Event.java
│           ├── repository/
│           │   └── EventRepository.java
│           ├── controller/
│           │   └── EventController.java
│           └── view/
│               └── EventView.java
└── test/
    └── java/
        └── org.factoria.controller/
                    └── EventControllerTest.java
```

## 📋 Estructura del proyecto

El proyecto sigue una arquitectura por capas basada en el patrón MVC:

| Capa                  | Descripción                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| **Modelo**            | Representa la estructura de los datos. Ej: `Event.java`                    |
| **Vista**             | Interactúa con el usuario a través de la consola. Ej: `EventView.java`     |
| **Controlador**       | Gestiona la lógica de negocio y conecta la vista con los datos. Ej: `EventController.java` |
| **Repositorio**       | Se encarga de las operaciones con la base de datos usando JDBC. Ej: `EventRepository.java` |
| **Gestor de conexión**| Controla la conexión con la base de datos MySQL. Ej: `DBManager.java`      |

---

## 🔄 ¿Cómo funciona el flujo completo?

1. **El usuario ejecuta la app** (`Main.java`).
2. **Se abre la conexión** a la base de datos.
3. **La vista** (`EventView`) pide datos al usuario o muestra eventos.
4. **La vista llama al controlador** (`EventController`).
5. **El controlador llama al repositorio** (`EventRepository`).
6. **El repositorio ejecuta SQL** para guardar o consultar datos.
7. **Los resultados vuelven** por el mismo camino hasta la vista.
8. **Se cierra la conexión** al terminar.

---

## 🛠️ PASO A PASO DETALLADO PARA CREAR LA APP

### 1. Agrega la dependencia de MySQL en tu pom.xml

**¿Qué hace?** Permite usar el conector JDBC de MySQL.

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

### 2. Agrega la dependencia de dotenv en tu pom.xml

**¿Qué hace?** Permite usar el .env con las variables de entorno.

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

### 3. Crea un archivo llamado .env en la raíz del proyecto

**¿Qué hace?** Configura las variables de entorno.  
**Ubicación:** Mismo nivel que pom.xml

```shell
# Archivo .env
DB_URL=jdbc:mysql://localhost:3306/tu_base_de_datos
DB_USER=root
DB_PASSWORD=tu_contraseña_secreta
DB_PORT=3306
```

### 4. Crea/actualiza tu .gitignore

**¿Qué hace?** Evita subir archivos sensibles al repositorio.

```shell
# .gitignore
.env
target/
*.iml
.idea/
```

### 5. Configura tu base de datos MySQL en Workbench

**En MySQL Workbench crea la base de datos y la tabla:**

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

### 6. 📁 `DBManager.java` (Configuración / Conexión)

**Qué es:** Clase encargada de gestionar la conexión con la base de datos.

**Función:**
- Abrir la conexión a la base de datos MySQL (usando JDBC).
- Proveer esa conexión a otras clases cuando la necesitan.
- Cerrar la conexión cuando la aplicación termina.

**Por qué es importante:** Centraliza la conexión y evita abrir múltiples conexiones, optimizando recursos.

**⚠️ No olvides utilizar las variables de entorno:**

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

### 7. 📁 `Event.java` (Modelo)

**Qué es:** Clase que representa el modelo de datos del sistema, en este caso un evento.

**Función:**
- Define los atributos que tiene un evento (`id`, `name`, `description`, `price`).
- Contiene los métodos getters y setters para acceder/modificar esos datos.
- Sirve como objeto para transportar datos dentro de la aplicación (DTO/POJO).

**Por qué es importante:** Define la estructura de los datos y es la base para manipular la información.

### 8. 📁 `EventRepository.java` (Repositorio / Acceso a datos)

**Qué es:** Clase encargada de manejar todas las operaciones de base de datos relacionadas con los eventos.

**Función:**
- Ejecutar sentencias SQL para guardar, buscar, actualizar o eliminar eventos.
- Convertir resultados SQL en objetos `Event` y viceversa.

**Por qué es importante:** Aísla la lógica de acceso a la base, manteniendo el resto de la app independiente de SQL/JDBC.

### 9. 📁 `EventController.java` (Controlador / Lógica de negocio)

**Qué es:** Clase que actúa como intermediario entre la vista y el repositorio.

**Función:**
- Recibe peticiones de la vista para realizar acciones (guardar, listar).
- Llama al repositorio para ejecutar esas acciones.
- Devuelve resultados a la vista.

**Por qué es importante:** Separa la lógica de negocio de la presentación y el acceso a datos, facilitando mantenimiento y escalabilidad.

### 10. 📁 `EventView.java` (Vista / Interfaz de usuario)

**Qué es:** Clase que se comunica con el usuario, en este caso por consola.

**Función:**
- Muestra menús y pide datos al usuario.
- Llama al controlador para realizar acciones con esos datos.
- Muestra resultados o mensajes por consola.

**Por qué es importante:** Encapsula la interacción con el usuario, permitiendo cambiar la interfaz sin afectar la lógica.

### 11. 📁 `Main.java` (Clase principal / Punto de entrada)

**Qué es:** Clase que inicia la ejecución de la aplicación.

**Función:**
- Abre la conexión con la base de datos.
- Instancia la vista para interactuar con el usuario.
- Ejecuta las funciones deseadas (guardar/listar eventos).
- Cierra la conexión al terminar.

**Por qué es importante:** Controla el ciclo de vida de la aplicación y el flujo principal.

---

## 🧪 PASO A PASO DETALLADO PARA HACER TESTING UNITARIOS

Para garantizar que el controlador funcione correctamente y que las dependencias como el repositorio sean llamadas adecuadamente, se implementan pruebas unitarias utilizando **JUnit 5** y **Mockito**.

### 1. Agregar dependencias en pom.xml

Agrega estas dependencias en la sección `<dependencies>` de tu archivo `pom.xml`:

```xml
<!-- JUNIT JUPITER: Framework para escribir y ejecutar tests -->
<!-- Solo se usa durante las pruebas, no en la aplicación final -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.12.2</version>
    <scope>test</scope> <!-- Solo disponible durante tests -->
</dependency>

<!-- MOCKITO: Para crear objetos "falsos" (mocks) en los tests -->
<!-- Útil para probar código sin depender de bases de datos reales, APIs, etc. -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.18.0</version>
    <scope>test</scope> <!-- Solo disponible durante tests -->
</dependency>
```

### 2. Configurar Maven para Java 21 + Mockito

Agrega esta configuración en la sección `<build>` de tu `pom.xml` para evitar warnings:

```xml
<build>
    <plugins>
        <!-- SUREFIRE PLUGIN: Se encarga de ejecutar los tests -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
            <configuration>
                <!-- 
                Parámetros para la JVM cuando ejecuta tests:
                -XX:+EnableDynamicAgentLoading: permite que Mockito funcione con Java 21+
                -Xshare:off: elimina warnings de Class Data Sharing
                -->
                <argLine>-XX:+EnableDynamicAgentLoading -Xshare:off</argLine>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 3. Crear archivo de prueba EventControllerTest.java

**Ubicación sugerida:** `src/test/java/org.factoria.controller/EventControllerTest.java`

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

    // @Mock: Crea un objeto simulado del repositorio
    // No ejecuta código real, solo simula el comportamiento
    @Mock
    private EventRepository eventRepository;

    // @InjectMocks: Crea el controlador e inyecta automáticamente el mock
    // Es como hacer: new EventController(eventRepository)
    @InjectMocks
    private EventController eventController;

    // @Test: Indica que este método es una prueba unitaria
    @Test
    void saveEventController_shouldCallRepository() {
        // ARRANGE (Preparar): creamos un evento ficticio para la prueba
        Event event = new Event("Evento de prueba", "Descripción de prueba", 50.0);

        // ACT (Actuar): ejecutamos el método que queremos probar
        eventController.saveEventController(event);

        // ASSERT (Verificar): comprobamos que el repositorio fue llamado correctamente
        // verify() verifica que el método saveEvent se llamó exactamente 1 vez con nuestro evento
        verify(eventRepository, times(1)).saveEvent(event);
    }
}
```

### 4. Ejecutar las pruebas

Puedes ejecutar las pruebas de estas formas:

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar solo una clase de test específica
mvn test -Dtest=EventControllerTest

# Limpiar, compilar y ejecutar tests
mvn clean test
```

### 🧪 ¿Qué estamos probando exactamente?

1. **Que el controlador llama correctamente al repositorio** - Verificamos que cuando guardamos un evento, el controlador efectivamente llama al método `saveEvent()` del repositorio.

2. **Aislamiento de dependencias** - No ejecutamos código SQL real gracias al uso de mocks. La base de datos no se usa en absoluto.

3. **Comportamiento específico** - Probamos solo la lógica del controlador, sin preocuparnos por si la base de datos funciona o no.

### 🔧 ¿Por qué usamos @Mock y @InjectMocks?

- **`@Mock`**: Crea un objeto "falso" de `EventRepository` que no hace operaciones reales de base de datos.
- **`@InjectMocks`**: Crea un `EventController` real y le inyecta automáticamente el mock del repositorio.
- **Resultado**: Podemos probar el controlador sin necesidad de tener MySQL funcionando.

### 📊 Patrón AAA en Testing

Nuestro test sigue el patrón **AAA**:

- **Arrange** (Preparar): Creamos los datos necesarios para la prueba
- **Act** (Actuar): Ejecutamos el método que queremos probar
- **Assert** (Verificar): Comprobamos que el resultado es el esperado

### 📋 Buenas prácticas para testing

✅ **Usa `verify()`** para asegurarte de que se llaman los métodos correctos con los parámetros esperados.

✅ **Usa mocks** cuando no quieras ejecutar lógica real (como conexiones a base de datos, llamadas a APIs, etc.).

✅ **Aísla cada unidad** que pruebes: prueba el controlador sin necesidad de probar el repositorio a la vez.

✅ **Nombres descriptivos**: El nombre del test debe explicar qué hace y qué espera.

✅ **Una verificación por test**: Cada test debe probar una sola cosa específica.

### 🚀 Comandos útiles para development

```bash
# Compilar sin ejecutar tests
mvn compile

# Ejecutar tests en modo verbose (más información)
mvn test -X

# Generar reporte de cobertura (si tienes JaCoCo configurado)
mvn test jacoco:report
```

### ⚠️ Notas importantes

- **Los tests no necesitan base de datos**: Gracias a los mocks, puedes ejecutar tests aunque MySQL esté apagado.
- **Rapidez**: Los tests unitarios son muy rápidos porque no hacen operaciones de I/O reales.
- **Independencia**: Cada test debe poder ejecutarse solo, sin depender de otros tests.