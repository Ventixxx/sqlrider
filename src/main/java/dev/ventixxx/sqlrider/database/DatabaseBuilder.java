package dev.ventixxx.sqlrider.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseBuilder extends Database {

    public DatabaseBuilder type(DatabaseType type) {
        TYPE = type;
        return this;
    }

    public DatabaseBuilder host(String host) {
        HOST = host;
        return this;
    }

    public DatabaseBuilder port(String port) {
        PORT = port;
        return this;
    }

    public DatabaseBuilder username(String username) {
        USERNAME = username;
        return this;
    }

    public DatabaseBuilder password(String password) {
        PASSWORD = password;
        return this;
    }

    public DatabaseBuilder database(String database) {
        DATABASE = database;
        return this;
    }

    public DatabaseContext build() {
        try {
            Connection connection = DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s", TYPE.name().toLowerCase(), HOST, PORT, DATABASE), USERNAME, PASSWORD);
            System.out.println("Connected to database!");
            return new DatabaseContext(connection);
        } catch (SQLException exception) {
            System.out.println("Failed: " + exception.getMessage());
        }
        System.out.println("Failed to connect to database!");
        return null;
    }

}
