package client.model.waiter;

import client.networking.customer.CustomerClient;
import client.networking.waiter.WaiterClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WaiterModelImpl implements WaiterModel {
    private PropertyChangeSupport support;
    private WaiterClient waiterClient;

    public WaiterModelImpl(){
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
    public void notificationReceived(String notification) {
         support.firePropertyChange("Notification received", null, notification);
    }
}
