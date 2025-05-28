// Declaración del paquete: ubica esta clase dentro del módulo 'controller' del proyecto
package org.factoria.controller;

// Importación de la clase Event (modelo de datos del evento)
import org.factoria.model.Event;

// Importación del repositorio que se encarga del acceso a la base de datos
import org.factoria.repository.EventRepository;

// Importación de la clase List de la biblioteca estándar de Java
// List es una interfaz que representa una colección de elementos ordenados
import java.util.List;

// Clase pública que actúa como controlador en el patrón MVC
// Su función es conectar la vista (usuario) con el repositorio (base de datos)
public class EventController {

    // Atributo privado y final (constante de solo lectura una vez inicializada)
    // Este atributo representa el repositorio que usaremos para acceder a los datos
    private final EventRepository eventRepository;

    // Constructor de la clase. Se llama cuando se crea un objeto EventController.
    // Recibe como parámetro una instancia de EventRepository.
    // Esto se llama inyección de dependencias: en lugar de crear el repositorio dentro del controlador,
    // lo recibimos desde afuera para hacer el código más flexible y fácil de probar.
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository; // Asignamos el repositorio recibido al atributo de clase
    }

    // Método público para guardar un evento.
    // Este método será llamado por la vista (interfaz de usuario) cuando el usuario quiera crear un nuevo evento.
    // Recibe un objeto Event como parámetro.
    // Luego, llama al método saveEvent() del repositorio para guardarlo en la base de datos.
    public void saveEventController(Event event) {
        eventRepository.saveEvent(event); // Llama al repositorio para guardar el evento
    }

    // Método público que devuelve una lista de todos los eventos.
    // Este método será llamado por la vista cuando el usuario quiera ver todos los eventos registrados.
    // Internamente llama al método findAll() del repositorio y retorna su resultado.
    public List<Event> findAllController() {
        return eventRepository.findAll(); // Retorna la lista de eventos obtenida del repositorio
    }
}
