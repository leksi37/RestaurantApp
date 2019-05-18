package client.model.waiter;

import java.beans.PropertyChangeSupport;

public class WaiterModelImpl implements WaiterModel {
    private PropertyChangeSupport support;

    public WaiterModelImpl(){
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void notificationReceived(String notification) {
         support.firePropertyChange("Notification received", null, notification);
    }
}
