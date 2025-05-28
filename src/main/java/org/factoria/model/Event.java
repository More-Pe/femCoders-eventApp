package org.factoria.model;

public class Event {
    private int id;
    private String name;
    private String description;
    private double price;

    public Event(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Event() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
