package com.trading.engine.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/trading";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("✅ Success! Connected to the trading database.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Connection failed!");
            System.err.println("Message: " + e.getMessage());
        }
    }
}