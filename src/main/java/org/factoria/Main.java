package org.factoria;

import org.factoria.config.DBManager;
import org.factoria.model.Event;
import org.factoria.repository.EventRepository;

public class Main {
    public static void main(String[] args) {
        //DBManager.initConnection();
        //DBManager.closeConnection();

        EventRepository eventRepository = new EventRepository();

        Event event = new Event("Evento 1", "El mejor evento del mundo", 55.5);
        Event event2 = new Event("Evento 2", "El mejor evento del mundo", 60.5);
        //eventRepository.createEvent(event);
        eventRepository.createEvent(event2);
    }
}