package client.networking.customer;

import basicClasses.MenuItem;
import basicClasses.Order;

import java.util.ArrayList;

public interface CustomerClient {

    void addOrderToServer(Order order);

    void requestMenuCategory(String type);

    void gotMenuItems(ArrayList<MenuItem> mi);

    void getTableId();

    void returnTableId(String obj);

    void orderAdded();

    void requestWaiter(String tableId);

    void requestReceipt(String tableId);
}