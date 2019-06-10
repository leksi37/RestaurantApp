package JDBC;

import basicClasses.CategoryType;
import basicClasses.MenuItem;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface MenuReader {
    void add(MenuItem item);
    void remove(String id);
    MenuItem getById(String id);
    MenuItem getByName(String name);
    ArrayList<MenuItem> getCategory(CategoryType tp);
    ArrayList<MenuItem> getAll();
    void change(MenuItem item);

}
