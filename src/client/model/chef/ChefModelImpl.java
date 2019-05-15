package client.model.chef;

import BasicClasses.Order;
import client.networking.chef.ChefClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChefModelImpl implements ChefModel {
    private ChefClient chefClient;
    private PropertyChangeSupport changeSupport;
    private ArrayList<Order> orders;

    public ChefModelImpl(){
        chefClient = null;
        changeSupport = new PropertyChangeSupport(this);
        orders = new ArrayList<>();
    }

    @Override
    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            changeSupport.addPropertyChangeListener(listener);
        else changeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void orderAdded(){
        changeSupport.firePropertyChange("OrderForChefAdded", null, orders);
    }

    @Override
    public void sendNotification(String notification) {
        chefClient.sendNotification(notification);
    }

    public void addOrder(Order order){
        orders.add(order);
        orderAdded();
    }

    @Override
    public void addClient(ChefClient chefClient) {
        this.chefClient = chefClient;
    }


}
