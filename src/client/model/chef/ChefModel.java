package client.model.chef;

import client.networking.chef.ChefClient;

import java.beans.PropertyChangeListener;

public interface ChefModel {
    void addListeners(String name, PropertyChangeListener listener);
    void orderAdded();
    void sendNotification(String notification);
    void addClient(ChefClient chefClient);
}
