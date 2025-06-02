package dev.ventixxx.sqlrider.database;

import dev.ventixxx.sqlrider.annotations.Column;
import dev.ventixxx.sqlrider.annotations.Table;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseContext {

    private final Connection connection;

    public DatabaseContext(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void migrate(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Missing @Table on " + clazz.getName());
        }
        String tableName = clazz.getAnnotation(Table.class).name();
        List<String> columns = new ArrayList<>();

        for(Field field : clazz.getDeclaredFields()) {
            String columnName = field.getName().toLowerCase();
            String sqlType = mapJavaTypeToSQL(field.getType());
            String columnDef = columnName + " " + sqlType;
            columns.add(columnDef);
        }
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                String.join(", ", columns) + ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table: " + tableName, e);
        }
    }

    private String mapJavaTypeToSQL(Class<?> type) {
        return switch (type.getSimpleName()) {
            case "String" -> "VARCHAR(255)";
            case "int", "Integer" -> "INT";
            case "long", "Long" -> "BIGINT";
            case "boolean", "Boolean" -> "BOOLEAN";
            case "double", "Double" -> "DOUBLE";
            default -> throw new UnsupportedOperationException("Unsupported type: " + type.getName());
        };
    }

}
