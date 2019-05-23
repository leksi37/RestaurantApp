package client.model.chef;

import basicClasses.Order;
import client.model.modelFactory.ClientModel;
import client.networking.chef.ChefClient;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface ChefModel extends ClientModel {
    void addListeners(String name, PropertyChangeListener listener);
    void orderAdded(Order order);
    void sendNotification(String notification);

    void checkLogIn(String value);

    void passwordDisapproved();

    void passwordApproved();

    void fetchOrders();

    void gotOrders(ArrayList<Order> obj);

    Order getOrder(int selectedIndex);

    void addedToOrder(Order obj);
}
