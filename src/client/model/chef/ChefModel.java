package client.model.chef;

import BasicClasses.Order;
import client.networking.Client;

import java.beans.PropertyChangeListener;

public interface ChefModel {
    void addListeners(String name, PropertyChangeListener listener);
    void orderAdded();
    void addOrder(Order order);

    void addClient(Client client);
}
