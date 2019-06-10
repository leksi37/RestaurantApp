package JDBC;

import basicClasses.Order;

import java.util.ArrayList;

public interface OrdersReader {
    void addOrder(Order order);
    Order readOrder(String tableId);
    ArrayList<Order> readAllOrders();
    void remove(String orderId);
}
