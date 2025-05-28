package org.factoria.controller;

import org.factoria.model.Event;
import org.factoria.repository.EventRepository;

import java.util.List;

public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEventController(Event event) {
        eventRepository.saveEvent(event);
    }

    public List<Event> findAllController() {
        return eventRepository.findAll();
    }
}
