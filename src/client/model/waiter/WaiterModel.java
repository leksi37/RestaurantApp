package client.model.waiter;

import client.model.logIn.modelFactory.ClientModel;

public interface WaiterModel extends ClientModel {

    void notificationReceived(String notification);
}
