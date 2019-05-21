package client.model.waiter;

import basicClasses.Notification;
import client.model.modelFactory.ClientModel;

import java.beans.PropertyChangeListener;

public interface WaiterModel extends ClientModel {
    void addListeners(String name, PropertyChangeListener listener);
    void notificationReceived(Notification notification);

    void presenceRequested(Notification presenceNotification);
}
