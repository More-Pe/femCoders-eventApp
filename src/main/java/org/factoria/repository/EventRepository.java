package org.factoria.repository;

import org.factoria.config.DBManager;
import org.factoria.model.Event;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EventRepository {
    private Connection connection;

    public void createEvent(Event event) {

        String querySQLCreate = "INSERT INTO events (name, description, price) VALUES ('"+ event.getName() + "', '"+ event.getDescription() + "', "+event.getPrice()+")";

        try {
            connection = DBManager.initConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(querySQLCreate);
            System.out.println("Evento creado");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());;
        } finally {
            DBManager.closeConnection();
        }
    }
}
