package client.model.chef;

import BasicClasses.Order;
import client.networking.chef.Client;

import java.beans.PropertyChangeListener;

public interface ChefModel {
    void addListeners(String name, PropertyChangeListener listener);
    void orderAdded();
    void sendNotification(String notification);
    void addClient(Client client);
}
