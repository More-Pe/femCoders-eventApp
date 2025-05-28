package org.factoria.view;

import org.factoria.controller.EventController;
import org.factoria.model.Event;

import java.util.List;
import java.util.Scanner;

public class EventView {
    private final EventController eventController;

    public EventView(EventController eventController){
        this.eventController = eventController;
    }

    public void saveEventView() {
        Event patata = generateEvent();
        eventController.saveEventController(patata);
    }

    public Event generateEvent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba el nombre del evento");
        String name = scanner.nextLine();
        System.out.println("Escriba la description del evento");
        String description = scanner.nextLine();
        System.out.println("Escriba el precio del evento");
        double price = scanner.nextDouble();

        Event event = new Event(name, description, price);

        scanner.close();

        return event;
    }

    public void showEvents() {
        List<Event> eventList = eventController.findAllController();

        for (Event event : eventList) {
            System.out.println("Name: " + event.getName() + " Description: " + event.getDescription());
        }
    }

}
