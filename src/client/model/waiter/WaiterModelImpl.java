package client.model.waiter;

import basicClasses.Notification;
import basicClasses.Order;
import client.networking.waiter.WaiterClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class WaiterModelImpl implements WaiterModel {
    private PropertyChangeSupport support;
    private WaiterClient waiterClient;
    private HashMap<String,Order> orders= new HashMap<>();

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
    public void notificationReceived(Notification notification) {
        support.firePropertyChange("Notification received", null, notification);
    }

    @Override
    public void presenceRequested(Notification notification) {
        support.firePropertyChange("customerRequest", null, notification);
    }

    @Override
    public void checkLogIn(String value) {
        waiterClient.checkPassword(value);
    }

    @Override
    public void passwordDisapproved() {
        support.firePropertyChange("passwordDisapproved", null, null);
    }

    @Override
    public void passwordApproved() {
        support.firePropertyChange("passwordApproved", null, null);
    }

    @Override
    public void chefRequest(Notification obj) {
        support.firePropertyChange("chefRequest", null,obj);
    }

    @Override
    public void partialToDeliver(Notification obj) {
        Order o= (Order) obj.getObject();
        String id=o.getTableId();
        orders.put(id, o);
        support.firePropertyChange("partial", null, obj);
    }

    @Override
    public void receiptRequest(Notification notification){
        support.firePropertyChange("Receipt request", null, notification);
    }

    @Override
    public Order getOrder(String id) {
        return orders.get(id);
    }

    @Override
    public void closeOrder(int lastSelectedTable) {
        waiterClient.closeOrder("table" + lastSelectedTable);
    }

    @Override
    public void orderClosed(String obj) {
        support.firePropertyChange("orderClosed", null, obj);
        orders.put(obj, null);
    }
}
