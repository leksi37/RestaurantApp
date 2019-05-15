package client.model.customer;

import BasicClasses.ItemQuantity;
import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.networking.customer.CustomerClient;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface CustomerModel {
    void addListeners(String name, PropertyChangeListener listener);
    void addClient(CustomerClient customerClient);

    void addOrderToServer();

    void requestMenuCategory(String type);

    void gotMenuItems(ArrayList<MenuItem> mi);

    void addItem(String id, int quantity);

    void gotTableId(String str);

    void removeItem(String id, int i);

    Order getOrder();

    void removeItem(ItemQuantity focusedItem);

    void orderAdded();

}
