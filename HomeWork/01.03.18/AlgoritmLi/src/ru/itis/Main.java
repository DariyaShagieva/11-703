package ru.itis;

import ru.itis.search.algoritm.algoritmLee.as.line.StaticLineLee;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        int [][] firstTry = {
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 0, -2, 0, -2, 0, -2},
//                {0, -2, 0, 0, 0, 0, -2, 0, 0, 0, 0},
//                {0, -2, -1, -2, 0, -2, 0, 0, -2, 0, 0}
//        };
//        for (int i = 10; i > 0; i++){
//            System.out.println(i);
//        }
        String dbUser = "postgres";
        String dbPassword = "140999";
        String connectionUrl = "jdbc:postgresql://localhost:5432/Users";

        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }
}
