package client.model.waiter;

import basicClasses.Notification;
import client.model.logIn.modelFactory.ClientModel;

import java.beans.PropertyChangeListener;

public interface WaiterModel extends ClientModel {

    void notificationReceived(Notification notification);
    void presenceRequested(Notification notification);
}
