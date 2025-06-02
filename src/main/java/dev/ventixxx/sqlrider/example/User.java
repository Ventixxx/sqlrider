package dev.ventixxx.sqlrider.example;

import dev.ventixxx.sqlrider.annotations.Column;
import dev.ventixxx.sqlrider.annotations.Table;

@Table(name = "users")
public final class User {

    private String username;
    private String firstName;
    private String lastName;

}
