package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    //public void createDatabase() {
    //    String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS mydatabase"; // Замените на нужное имя базы данных
    //    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
    //         Statement statement = connection.createStatement()) {
    //        statement.executeUpdate(createDatabaseQuery);
    //        System.out.println("База данных 'mydatabase' успешно создана!");
    //    } catch (SQLException e) {
    //        e.printStackTrace();
    //        System.out.println("Не удалось создать базу данных.");
    //    }
    //}

    public void createUsersTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS mydatabase.Users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age TINYINT)";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "pqGRSU123");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
            System.out.println("Таблица 'Users' успешно создана!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось создать таблицу 'Users'.");
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS mydatabase.Users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserQuery = "INSERT INTO Users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(saveUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String query = "DELETE FROM Users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM Users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
