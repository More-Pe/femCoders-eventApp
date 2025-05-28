package org.factoria;

import org.factoria.config.DBManager;
import org.factoria.controller.EventController;
import org.factoria.model.Event;
import org.factoria.repository.EventRepository;
import org.factoria.view.EventView;

public class Main {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        EventController eventController = new EventController(eventRepository);
        EventView eventView = new EventView(eventController);
        //eventView.saveEventView();

        eventView.showEvents();
    }
}