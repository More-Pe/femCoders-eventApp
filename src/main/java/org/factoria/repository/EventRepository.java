// Paquete donde se encuentra esta clase (organización del proyecto)
package org.factoria.repository;

// Importamos la clase que gestiona la conexión a la base de datos
import org.factoria.config.DBManager;

// Importamos la clase Event (modelo de datos)
import org.factoria.model.Event;

// Librerías de JDBC (Java Database Connectivity) para trabajar con bases de datos
import java.sql.*; // Incluye Connection, PreparedStatement, ResultSet, SQLException
import java.util.ArrayList;
import java.util.List;

// Clase que se encarga de acceder a la base de datos para operaciones con la tabla "events"
public class EventRepository {

    // Atributo para guardar la conexión a la base de datos (java.sql.Connection)
    private Connection connection;

    // Método para guardar un evento en la base de datos (operación CREATE del CRUD)
    public void saveEvent(Event event) {

        // Consulta SQL con placeholders (?) para evitar inyecciones SQL
        // Los valores se asignan más adelante con PreparedStatement
        String querySQLCreate = "INSERT INTO events (name, description, price) VALUES (?, ?, ?)";

        try {
            // Obtenemos la conexión a la base de datos usando nuestra clase DBManager
            connection = DBManager.initConnection();

            // Creamos un PreparedStatement (forma segura de ejecutar consultas con valores)
            PreparedStatement statement = connection.prepareStatement(querySQLCreate);

            // Asignamos los valores del objeto Event a los signos de interrogación (?)
            statement.setString(1, event.getName());        // Primer parámetro: name
            statement.setString(2, event.getDescription()); // Segundo parámetro: description
            statement.setDouble(3, event.getPrice());       // Tercer parámetro: price

            // Ejecutamos la consulta SQL (INSERT en este caso)
            statement.execute();

            // Mensaje de confirmación
            System.out.println("Evento creado");
        } catch (SQLException exception) {
            // Si ocurre un error de SQL, lo imprimimos
            System.out.println(exception.getMessage());
        } finally {
            // Cerramos la conexión sin importar si hubo error o no
            DBManager.closeConnection();
        }
    }

    // Método para obtener todos los eventos de la base de datos (operación READ del CRUD)
    public List<Event> findAll() {
        // Inicializamos una lista vacía donde guardaremos los eventos
        List<Event> events = new ArrayList<>();

        // Consulta SQL para obtener todos los registros de la tabla
        String querySQLSelectALl = "SELECT * FROM events";

        try {
            // Obtenemos la conexión
            connection = DBManager.initConnection();

            // Preparamos y ejecutamos la consulta
            PreparedStatement statement = connection.prepareStatement(querySQLSelectALl);
            ResultSet response = statement.executeQuery(); // Resultado de la consulta (tabla)

            // Iteramos por cada fila del resultado
            while(response.next()) {
                // Obtenemos cada campo del registro actual
                String name = response.getString("name");               // Campo "name"
                String description = response.getString("description");// Campo "description"
                double price = response.getDouble("price");            // Campo "price"

                // Creamos un nuevo objeto Event con los datos obtenidos
                Event event = new Event(name, description, price);

                // Lo añadimos a la lista
                events.add(event);
            }
        } catch (SQLException exception) {
            // Mostramos el error si ocurre
            System.out.println(exception.getMessage());
        } finally {
            // Cerramos la conexión
            DBManager.closeConnection();
        }

        // Devolvemos la lista de eventos
        return events;
    }
}
