package dev.ventixxx.sqlrider;

import dev.ventixxx.sqlrider.database.DatabaseBuilder;
import dev.ventixxx.sqlrider.database.DatabaseContext;
import dev.ventixxx.sqlrider.database.DatabaseType;
import dev.ventixxx.sqlrider.example.User;

public final class SQLRider {

    static DatabaseContext context;

    public static void main(String[] args) {
        context = new DatabaseBuilder()
                .type(DatabaseType.MYSQL)
                .host("127.0.0.1")
                .port("3306")
                .username("root")
                .password("root")
                .database("minecraft")
                .build();

        assert context != null;
        context.migrate(User.class);
    }

}
