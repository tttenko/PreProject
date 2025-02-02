package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/schema_name";
    private static final String ROOT = "root";
    private static final String PASSWORD = "Qqwweerrttyy2003";

    private static void getConnection() {
        try (Connection connection = DriverManager.getConnection(URL, ROOT, PASSWORD)) {
            if (!connection.isClosed()) {
                System.out.println("Connection success!");
            }
        } catch (SQLException e) {
            System.out.println("Not connection! " + e.getMessage());
        }
    }
}
