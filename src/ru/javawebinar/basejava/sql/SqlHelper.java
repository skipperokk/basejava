package ru.javawebinar.basejava.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sqlQuery) {
        execute(sqlQuery, PreparedStatement::execute);
    }


    public <T> T execute(String sqlQuery, SqlExecutor<T> sqlExecutor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            return sqlExecutor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }
}
