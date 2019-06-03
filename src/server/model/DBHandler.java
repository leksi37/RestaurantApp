package server.model;

import JDBC.*;
import basicClasses.CategoryType;
import basicClasses.MenuItem;
import basicClasses.Order;
import basicClasses.Passwords;

import java.util.ArrayList;

public class DBHandler {
    private PasswordsReader passwordReader;
    private OrdersReader orderReader;
    private MenuReader itemsReader;

    public DBHandler() {
        passwordReader = new PasswordReader();
        orderReader = new OrderReader();
        itemsReader = new MenuItemsReader();
    }

    public void addOrder(Order order)
    {
        orderReader.addOrder(order);
    }

    public void removeOrder(String id)
    {
        orderReader.remove(id);
    }

    public Order getOrder(String id)
    {
        return orderReader.readOrder(id);
    }

    public ArrayList<Order> getAllOrders()
    {
        return orderReader.readAllOrders();
    }

    public ArrayList<MenuItem> getCategory(CategoryType category)
    {
        return itemsReader.getCategory(category);
    }

    public void addItem(MenuItem item)
    {
        itemsReader.add(item);
    }

    public Passwords getPassword(String user)
    {
        return passwordReader.getPassword(user);
    }

    public void changePasswords(Passwords password)
    {
        passwordReader.changePassword(password);
    }
}
