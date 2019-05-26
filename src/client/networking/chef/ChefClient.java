package client.networking.chef;

import basicClasses.Order;

public interface ChefClient {

    void sendNotification(String notification);
    void gotOrder(Order order);
    void checkPassword(String value);
    void passwordApproved();
    void passwordDisapproved();
}
