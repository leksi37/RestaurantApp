package client.model.customer;

import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.networking.Client;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface CustomerModel {
    void addListeners(String name, PropertyChangeListener listener);
    void addClient(Client client);

    void addOrderToServer(Order order);

    //this one is only for testing
    void getFromServer();

    void menuCategory(ArrayList a);
    void requestMenuCategory(String type);

    void gotMenuItems(ArrayList<MenuItem> mi);

    void addItem(String id, int quantity);

    void gotTableId(String str);

    void removeItem(String id, int i);

    Order getOrder();
}
