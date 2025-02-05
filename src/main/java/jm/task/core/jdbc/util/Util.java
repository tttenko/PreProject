package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/preproject";
    private static final String ROOT = "root";
    private static final String PASSWORD = "Qqwweerrttyy2003";

    public static Connection connectionJDBC() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println("Not connection! " + e.getMessage());
        }
        return connection;
    }

    public static Session connectionHibernate() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

}
