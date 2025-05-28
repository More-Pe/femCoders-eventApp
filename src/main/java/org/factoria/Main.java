// Declaramos que esta clase está en el paquete principal de la aplicación
package org.factoria;

// Importamos las clases necesarias del resto de la aplicación
// Estas importaciones permiten usar los objetos de configuración, controlador, repositorio, modelo y vista
import org.factoria.config.DBManager;
import org.factoria.controller.EventController;
import org.factoria.model.Event;
import org.factoria.repository.EventRepository;
import org.factoria.view.EventView;

// Clase principal que inicia la ejecución del programa (entry point)
public class Main {

    // Método main: el primero que se ejecuta al correr la aplicación
    public static void main(String[] args) {

        // Creamos una instancia del repositorio, que maneja la conexión con la base de datos
        EventRepository eventRepository = new EventRepository();

        // Creamos una instancia del controlador, pasándole el repositorio
        // El controlador usará este repositorio para acceder a los datos
        EventController eventController = new EventController(eventRepository);

        // Creamos una instancia de la vista, pasándole el controlador
        // La vista interactuará con el usuario y delegará acciones al controlador
        EventView eventView = new EventView(eventController);

        // Esta línea está comentada, pero si la descomentas permite guardar un nuevo evento
        // eventView.saveEventView();

        // Este método muestra todos los eventos disponibles (ya guardados en la base de datos)
        eventView.showEvents();
    }
}
