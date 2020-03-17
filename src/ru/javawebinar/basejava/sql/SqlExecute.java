package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecute<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
