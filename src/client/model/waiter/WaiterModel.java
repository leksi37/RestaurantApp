package client.model.waiter;

import basicClasses.Notification;
import basicClasses.Order;
import client.model.logIn.modelFactory.ClientModel;

public interface WaiterModel extends ClientModel {

    void notificationReceived(Notification notification);

    void presenceRequested(Notification notification);

    void checkLogIn(String value);

    void passwordDisapproved();

    void passwordApproved();

    void chefRequest(Notification obj);

    void partialToDeliver(Notification obj);

    void receiptRequest(Notification notification);
}