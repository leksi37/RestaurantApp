package client.model.waiter;

import basicClasses.Notification;
import client.model.logIn.modelFactory.ClientModel;


public interface WaiterModel extends ClientModel {

    void notificationReceived(Notification notification);
}
