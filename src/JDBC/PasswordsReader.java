package JDBC;

import basicClasses.Passwords;

public interface PasswordsReader {
    void add(Passwords p);
    Passwords getPassword(String name);
    void changePassword(Passwords password);
}
