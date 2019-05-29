package client.networking.chef;

import basicClasses.Order;

import java.util.ArrayList;

public interface ChefClient {
    void gotOrder(Order order);

    void checkPassword(String value);

    void passwordApproved();

    void passwordDisapproved();

    void fetchOrders();

    void gotOrders(ArrayList<Order> obj);

    void addedToOrder(Order obj);

    void itemStateChanged(Order order);

    void orderChanged(Order obj);

    void sendPartial(Order order);

    void orderFinished(String tableId);

    void removeOrder(String obj);

    void requestWaiter();

    void partialSent(Order obj);
}
