package server.model;

import JDBC.MenuItemsReader;
import JDBC.OrderReader;
import JDBC.PasswordReader;
import basicClasses.MenuItem;
import basicClasses.Order;
import basicClasses.Passwords;
import basicClasses.type;
import sun.security.util.Password;

import java.util.ArrayList;

public class DBHandler {
    private PasswordReader passwordReader;
    private OrderReader orderReader;
    private MenuItemsReader itemsReader;

    public DBHandler() {
        passwordReader = PasswordReader.getInstance();
        orderReader = OrderReader.getInstance();
        itemsReader = MenuItemsReader.getInstance();
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

    public ArrayList<MenuItem> getCategory(type category)
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
