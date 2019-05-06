package client.model.chef;

import BasicClasses.Order;
import client.networking.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChefModelImpl implements ChefModel {
    private Client client;
    private PropertyChangeSupport changeSupport;
    private ArrayList<Order> orders;

    public ChefModelImpl(){
        client = null;
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

    public void addOrder(Order order){
        orders.add(order);
        orderAdded();
    }

    @Override
    public void addClient(Client client) {

    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    public void orderDoneChef(Order order){
        client.markOrderAsDone(order);
    }

}
