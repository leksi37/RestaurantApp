package client.model.chef;

import basicClasses.Order;
import client.model.modelFactory.ClientModel;

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

//    void itemAddedToPartialOrder(String id, int selectedIndex);

    void sendPartial(int i);

    void nextState(String id, int selectedIndex);

    void orderChanged(Order order);
}
