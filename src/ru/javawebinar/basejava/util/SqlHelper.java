package ru.javawebinar.basejava.util;

import sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;
    private String dbUrl = "jdbc:postgresql://localhost:5432/resumes";
    private String dbUser = "postgres";
    private String dbPassword = "admin";


    private SqlHelper(String SqlQuery) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
