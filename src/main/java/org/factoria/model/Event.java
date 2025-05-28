// Este archivo forma parte del paquete org.factoria.model
// Sirve para organizar lógicamente las clases del proyecto
package org.factoria.model;

// Clase pública que representa un modelo de "Evento" en la aplicación.
// Es un POJO (Plain Old Java Object), es decir, una clase simple que solo almacena datos.
public class Event {

    // Atributo privado que representa el ID único del evento.
    // En una base de datos, este campo usualmente es la clave primaria.
    private int id;

    // Nombre del evento (por ejemplo: "Concierto de Jazz").
    private String name;

    // Breve descripción del evento (por ejemplo: "Concierto en vivo con músicos locales").
    private String description;

    // Precio del evento. Usamos double para permitir valores con decimales.
    private double price;

    // Constructor vacío (sin parámetros).
    // Es obligatorio para algunos frameworks (como Hibernate o Spring)
    // que necesitan crear objetos sin datos iniciales.
    public Event() {}

    // Constructor con parámetros (sobrecarga del constructor).
    // Permite crear objetos de tipo Event directamente con valores.
    // No incluye el ID porque normalmente ese valor lo genera la base de datos automáticamente.
    public Event(String name, String description, double price) {
        this.name = name;                 // Asignamos el nombre recibido al atributo name
        this.description = description;   // Asignamos la descripción recibida al atributo description
        this.price = price;               // Asignamos el precio recibido al atributo price
    }

    // Métodos "getters" y "setters"
    // Sirven para acceder o modificar los atributos privados de la clase.
    // Siguen una convención estándar en Java.

    // Getter para el atributo id
    public int getId() {
        return id;
    }

    // Setter para el atributo id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para name (nombre del evento)
    public String getName() {
        return name;
    }

    // Setter para name
    public void setName(String name) {
        this.name = name;
    }

    // Getter para description (descripción del evento)
    public String getDescription() {
        return description;
    }

    // Setter para description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter para price (precio del evento)
    public double getPrice() {
        return price;
    }

    // Setter para price
    public void setPrice(double price) {
        this.price = price;
    }
}
