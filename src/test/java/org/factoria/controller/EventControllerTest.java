// Este archivo pertenece al paquete de pruebas del controlador de eventos
package org.factoria.controller;

// Se importan las clases necesarias: el modelo, el repositorio, JUnit y Mockito
import org.factoria.model.Event;
import org.factoria.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// Esta anotación le indica a JUnit que esta clase usará funcionalidades de Mockito.
// Es necesaria para que las anotaciones @Mock y @InjectMocks funcionen correctamente.
@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    // Se crea un "mock" del EventRepository.
    // Un mock es un objeto simulado que imita el comportamiento del objeto real, pero sin ejecutar su lógica real.
    // Esto evita hacer llamadas reales a la base de datos durante las pruebas.
    @Mock
    private EventRepository eventRepository;

    // Se crea una instancia de EventController y se le inyecta automáticamente el mock de EventRepository.
    // Esto funciona porque EventController depende de EventRepository, normalmente a través del constructor.
    // Así podemos probar el controlador sin necesidad de usar una implementación real del repositorio.
    @InjectMocks
    private EventController eventController;

    // Este es un método de prueba. Verifica que el método saveEventController del controlador
    // efectivamente llame al método saveEvent del repositorio una vez, como se espera.
    @Test
    void saveEventController_shouldCallRepository() {
        // Arrange: Se crea un objeto Event de prueba.
        // No se le asigna un ID porque todavía no ha sido guardado en la base de datos.
        Event event = new Event("Evento 1", "el mejor", 63.2);

        // Act: Se llama al método que queremos probar, pasando el evento de prueba.
        // Esto debería delegar la operación de guardado al repositorio.
        eventController.saveEventController(event);

        // Assert: Verificamos que el método saveEvent del repositorio fue llamado exactamente una vez,
        // y que fue llamado con el mismo objeto que pasamos. Esto se hace con el método verify() de Mockito.
        verify(eventRepository, times(1)).saveEvent(event);
    }
}
