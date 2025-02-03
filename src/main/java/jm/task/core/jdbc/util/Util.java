package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pre_project";
    private static final String ROOT = "root";
    private static final String PASSWORD = "Qqwweerrttyy2003";

    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Not connection! " + e.getMessage());
        }
        return connection;
    }
}
