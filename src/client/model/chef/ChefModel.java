package client.model.chef;

import basicClasses.Order;
import client.model.logIn.modelFactory.ClientModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface ChefModel extends ClientModel {
    void orderAdded(Order order);

    void checkLogIn(String value);

    void passwordDisapproved();

    void passwordApproved();

    void fetchOrders();

    void gotOrders(ArrayList<Order> obj);

    Order getOrder(int selectedIndex);

    void addedToOrder(Order obj);

    void sendPartial(int i);

    void nextState(String id, int selectedIndex);

    void orderFinished(int lastSelected);

    void removeOrder(String obj);

    void requestWaiter();

    void partialSent(Order obj);

    void sendFinishedItems(int lastSelected);
}
