package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS human_2 (id int NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                                                        "name varchar(50), " +
                                                        "lastName varchar(50), " +
                                                        "age int)";
        try (Connection connection = Util.connectionJDBC()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
            System.out.println("Table was success created");

        } catch (SQLException e) {
            System.out.println("Exception by create table " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String deleteTableSQL = "DROP TABLE IF EXISTS human_2";
        try (Connection connection = Util.connectionJDBC()) {

            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteTableSQL);
            System.out.println("Table success was deleted");

        } catch (SQLException e) {
            System.out.println("Exception by delete table " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, Integer age) {
        String saveUserSQL = "INSERT INTO human_2 (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.connectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(saveUserSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User was success added!");

        } catch (SQLException e) {
            System.out.println("Exception by add user: " + e.getMessage());
        }
    }

    public void removeUserById(Integer id) {
        String deleteSQL = "DELETE FROM human_2 WHERE id = '" + id + "'";
        try (Connection connection = Util.connectionJDBC()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteSQL);
            System.out.println("User was success removed");
        } catch (SQLException e) {
            System.out.println("Exception by remove user " + e.getMessage());;
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String getAllSQL = "SELECT * FROM human_2";

        try (Connection connection = Util.connectionJDBC()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllSQL);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getInt("age"));

                listUser.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Exception by add user in list");
        }
        System.out.println("Success was added users in list");

        return listUser;
    }

    public void cleanUsersTable() {
        String cleanSQL = "DELETE FROM human_2";

        try (Connection connection = Util.connectionJDBC()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(cleanSQL);
            System.out.println("Table was success cleaned");
        } catch (SQLException e) {
            System.out.println("Exception by clean table");
        }

    }
}
