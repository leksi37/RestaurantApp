package client.model.customer;


import basicClasses.ItemQuantity;
import basicClasses.MenuItem;
import basicClasses.Order;
import client.model.modelFactory.ClientModel;
import client.networking.customer.CustomerClient;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface CustomerModel extends ClientModel {
    void addListeners(String name, PropertyChangeListener listener);

    void addOrderToServer(String note);

    void requestMenuCategory(String type);

    void gotMenuItems(ArrayList<MenuItem> mi);

    void addItem(String id, int quantity);

    void gotTableId(String str);

    void removeItem(String id, int i);

    Order getOrder();

    void removeItem(ItemQuantity focusedItem);

    void orderAdded();

}
