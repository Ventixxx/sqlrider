package dev.ventixxx.sqlrider.api;

import dev.ventixxx.sqlrider.database.Database;
import dev.ventixxx.sqlrider.database.DatabaseBuilder;
import dev.ventixxx.sqlrider.database.DatabaseContext;
import dev.ventixxx.sqlrider.database.DatabaseType;

public final class SQLRider {

    private String DBHost;
    private String DBPort;
    private String DBDatabase;

    public SQLRider(String DBHost, String DBPort, String DBDatabase) {
        DatabaseContext context = new DatabaseBuilder()
                .type(DatabaseType.MYSQL)
                .host(DBHost)
                .port(DBPort)
                .database(DBDatabase)
                .build();
    }

    public void migrate() {

    }

}
