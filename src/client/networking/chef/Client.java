package client.networking.chef;

import BasicClasses.Order;

public interface Client {
    void sendNotification(String notification);
    void gotOrder(Order order);
}
