package org.factoria.repository;

import org.factoria.config.DBManager;
import org.factoria.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    //CRUD
    private Connection connection;

    public void saveEvent(Event event) {

        /*String querySQLCreate = "INSERT INTO events (name, description, price) VALUES ('"+ event.getName() + "', '"+ event.getDescription() + "', "+event.getPrice()+")";*/
        String querySQLCreate = "INSERT INTO events (name, description, price) VALUES (?, ?, ?)";

        try {
            connection = DBManager.initConnection();
            //Statement statement = connection.createStatement();
            //statement.executeUpdate(querySQLCreate);

            PreparedStatement statement = connection.prepareStatement(querySQLCreate);
            statement.setString(1, event.getName());
            statement.setString(2, event.getDescription());
            statement.setDouble(3, event.getPrice());
            statement.execute();

            System.out.println("Evento creado");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
    }

    public List<Event> findAll() {
        //inicializar la colecion vacia
        List<Event> events = new ArrayList<>();

        String querySQLSelectALl = "SELECT * FROM events";

        try {
            connection = DBManager.initConnection();
            PreparedStatement statement = connection.prepareStatement(querySQLSelectALl);
            ResultSet response = statement.executeQuery();

            while(response.next()) {
                String name = response.getString("name");
                String description = response.getString("description");
                double price = response.getDouble("price");

                //crear objeto
                Event event = new Event(name, description, price);
                events.add(event);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        } finally {
            DBManager.closeConnection();
        }
        return events;
    }

}
