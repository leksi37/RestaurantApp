package client.model.waiter;

import basicClasses.Notification;
import client.networking.waiter.WaiterClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WaiterModelImpl implements WaiterModel {
    private PropertyChangeSupport support;
    private WaiterClient waiterClient;

    public WaiterModelImpl(){
        this.waiterClient = null;
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void addClient(Object object) {
        if(object instanceof WaiterClient)
        {
            this.waiterClient=(WaiterClient)object;
        }
    }

    @Override
    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void notificationReceived(Notification notification) {
         support.firePropertyChange("Notification received", notification.getNotificationText(), notification);

         //testing something
        System.out.println(notification.getNotificationText());
    }

//    @Override
//    public void presenceRequested(Notification notification) {
//        support.firePropertyChange("Presence notification received", notification.getNotificationText(), notification.getNotificationText());
//    }
}
