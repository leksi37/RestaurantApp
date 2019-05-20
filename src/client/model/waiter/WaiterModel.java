package client.model.waiter;

import client.model.modelFactory.ClientModel;

public interface WaiterModel extends ClientModel {

    void notificationReceived(String notification);
}
