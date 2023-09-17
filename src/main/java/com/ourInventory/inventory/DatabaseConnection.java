package com.ourInventory.inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection connection = null;
        String jdbcUrl = "jdbc:postgresql://192.168.108.239:5432/inventory";
        String username = "postgres";
        String password = "oleg";
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Зашел");
        return connection;
    }
}
