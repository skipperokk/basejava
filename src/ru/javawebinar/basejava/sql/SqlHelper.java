package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sqlQuery, SqlExecute<T> sqlExecute) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            return sqlExecute.execute(ps);
        } catch (SQLException e) {
            throw new ExistStorageException(null);
        }
    }
}
