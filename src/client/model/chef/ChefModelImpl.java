package client.model.chef;

import basicClasses.Order;
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
    public void addClient(Object object) {
        if(object instanceof ChefClient)
        {
            this.chefClient=(ChefClient)object;
        }
    }

    @Override
    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            changeSupport.addPropertyChangeListener(listener);
        else changeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void orderAdded(Order order){
        orders.add(order);
        changeSupport.firePropertyChange("OrderForChefAdded", null, orders);
    }

    @Override
    public void sendNotification(String notification) {
        chefClient.sendNotification(notification);
    }

    @Override
    public void checkLogIn(String value) {
        System.out.println("model" + value);
        chefClient.checkPassword(value);
    }

    @Override
    public void passwordDisapproved() {
        changeSupport.firePropertyChange("chefPasswordDisapproved", null, null);
    }

    @Override
    public void passwordApproved() {
        changeSupport.firePropertyChange("chefPasswordApproved", null, null);
    }

    @Override
    public void fetchOrders() {
        System.out.println("chef model");
        chefClient.fetchOrders();
    }

    @Override
    public void gotOrders(ArrayList<Order> obj) {
        orders = obj;
        changeSupport.firePropertyChange("gotOrders", null, obj);
    }

    @Override
    public Order getOrder(int selectedIndex) {
        System.out.println("" + selectedIndex + "," + orders.size());
        return orders.get(selectedIndex);
    }

    @Override
    public void addedToOrder(Order obj) {
        for(int i = 0; i < orders.size(); ++i)
            if(orders.get(i).getTableId().equals(obj.getTableId()))
            {
                orders.remove(i);
                orders.add(i, obj);
            }
        changeSupport.firePropertyChange("gotOrder", null, obj);
    }

}
