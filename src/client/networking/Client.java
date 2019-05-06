package client.networking;


import BasicClasses.MenuItem;
import BasicClasses.Order;

import java.util.ArrayList;

public interface Client {

    void addOrderToServer(Order order);
    void orderAddedToServer(Order order);
    void requestMenuCategory(String type);

    void gotMenuItems(ArrayList<MenuItem> mi);

     void getTableId();

    void returnTableId(String obj);

    void orderAdded();
//
//    void markOrderAsDone(Order order);
//
//    void orderReceived(Order obj);
}
