package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    final static String URL = "jdbc:mysql://localhost:3306/mydatabase";
    final static String USERNAME = "root";
    final static String PASSWORD = "pqGRSU123";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к базе данных!");
        }
        return connection;
    }
}
