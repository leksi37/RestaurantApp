package client.networking.chef;

import BasicClasses.Order;

public interface ChefClient {
    void sendNotification(String notification);
    void gotOrder(Order order);
}
