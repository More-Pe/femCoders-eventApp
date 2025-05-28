// Declaración del paquete: esta clase forma parte de la capa de "vista" (interfaz con el usuario)
package org.factoria.view;

// Importamos el controlador, que usaremos para delegar la lógica de negocio
import org.factoria.controller.EventController;

// Importamos el modelo Event, que representa un evento
import org.factoria.model.Event;

// Importamos clases estándar de Java: List para manejar listas y Scanner para leer datos del usuario por consola
import java.util.List;
import java.util.Scanner;

// Clase pública que representa la interfaz de usuario (en consola) del sistema
// Esta clase se encarga de interactuar con el usuario, pedirle datos y mostrarle resultados
public class EventView {

    // Atributo privado que guarda una instancia del controlador (parte del MVC)
    private final EventController eventController;

    // Constructor de la clase. Recibe una instancia del controlador por inyección de dependencias
    // Esto hace que sea más flexible y más fácil de testear
    public EventView(EventController eventController){
        this.eventController = eventController;
    }

    // Método para crear y guardar un evento (llamado desde el main, por ejemplo)
    public void saveEventView() {
        // Llamamos al método generateEvent() que pide al usuario los datos del evento
        Event patata = generateEvent();

        // Enviamos ese evento al controlador para que lo guarde
        eventController.saveEventController(patata);
    }

    // Método que interactúa con el usuario para crear un evento
    // Pide los datos por consola, construye el objeto Event y lo retorna
    public Event generateEvent() {
        // Creamos un escáner para leer por consola
        Scanner scanner = new Scanner(System.in);

        // Pedimos el nombre del evento
        System.out.println("Escriba el nombre del evento");
        String name = scanner.nextLine();

        // Pedimos la descripción
        System.out.println("Escriba la description del evento");
        String description = scanner.nextLine();

        // Pedimos el precio
        System.out.println("Escriba el precio del evento");
        double price = scanner.nextDouble();

        // Creamos un nuevo objeto Event con los datos ingresados
        Event event = new Event(name, description, price);

        // Cerramos el escáner (buena práctica, aunque en consola pequeña puede causar problemas si se usa varias veces)
        scanner.close();

        // Devolvemos el evento creado
        return event;
    }

    // Método para mostrar todos los eventos registrados
    public void showEvents() {
        // Pedimos al controlador la lista de eventos (ya los obtiene desde el repositorio)
        List<Event> eventList = eventController.findAllController();

        // Iteramos sobre la lista y mostramos los datos de cada evento
        for (Event event : eventList) {
            System.out.println("Name: " + event.getName() + " Description: " + event.getDescription());
        }
    }
}
