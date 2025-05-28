// Paquete donde se encuentra esta clase, útil para organizar el proyecto
package org.factoria.config;

// Importamos la clase Dotenv de la librería externa dotenv-java
// Se usa para cargar variables de entorno desde un archivo .env
import io.github.cdimascio.dotenv.Dotenv;

// Importaciones de Java para conectarse a bases de datos usando JDBC
import java.sql.Connection;       // Representa una conexión a la base de datos
import java.sql.DriverManager;    // Clase que permite crear conexiones
import java.sql.SQLException;     // Excepción que se lanza cuando algo falla con la BD

// Clase pública que gestiona la conexión a la base de datos
public class DBManager {

    // Variable privada y estática para cargar las variables de entorno
    // Dotenv.configure() -> método predefinido de la librería dotenv-java
    // directory("./") indica que buscará el archivo .env en la raíz del proyecto
    // load() carga las variables del archivo .env
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("./")
            .load();

    // Constantes privadas para guardar los datos de conexión leídos del .env
    // Estas variables deben estar definidas en el archivo .env como:
    // DB_URL=...
    // DB_USER=...
    // DB_PASSWORD=...
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    // Variable estática para mantener una única conexión abierta en toda la app
    private static Connection connection;

    // Método público y estático que inicializa la conexión a la base de datos
    // Retorna un objeto de tipo java.sql.Connection
    public static Connection initConnection() {
        // Verifica que las variables de entorno se hayan cargado correctamente
        // Si alguna es null, lanza una excepción en tiempo de ejecución
        if (URL == null || USER == null || PASSWORD == null) {
            throw new RuntimeException("Variables de entorno no configuradas correctamente");
        }

        try {
            // DriverManager.getConnection() es un método predefinido de Java (JDBC)
            // Intenta establecer una conexión con la base de datos usando los datos proporcionados
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Si tiene éxito, imprime un mensaje de éxito
            System.out.println("Conexión exitosa");
        } catch (SQLException exception) {
            // Si hay un error al conectar, lo captura y muestra el mensaje
            System.out.println("Error de conexión: " + exception.getMessage());
        }

        // Devuelve el objeto de conexión (puede ser null si falló)
        return connection;
    }

    // Método público y estático para cerrar la conexión cuando ya no se necesita
    public static void closeConnection() {
        try {
            // Verifica que la conexión exista y que no esté ya cerrada
            if (connection != null && !connection.isClosed()) {
                // Cierra la conexión (método de java.sql.Connection)
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException exception) {
            // Si hay un error al cerrar, lo captura y muestra el mensaje
            System.out.println("Error al cerrar conexión: " + exception.getMessage());
        }
    }
}
