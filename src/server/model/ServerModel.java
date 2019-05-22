package server.model;

import basicClasses.Notification;
import basicClasses.Order;
import server.ServerSocketHandlerClientIds;
import server.networking.ServerSocketHandler;
import server.networking.ServerSocketHandlers.ChefServerSocketHandler;
import server.networking.ServerSocketHandlers.CustomerServerSocketHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModel {

    private ArrayList<Order> orders;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private ArrayList<ServerSocketHandler> connections;
    private int customerCounter;
    private int chefCounter;
    private int waiterCounter;

    public ServerModel() {
        orders= new ArrayList<>();
        customerCounter = 0;
        chefCounter = 0;
        waiterCounter = 0;
        connections = new ArrayList<ServerSocketHandler>();
    }

    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    public void addOrder(Order order){
        orders.add(order);
        support.firePropertyChange("AddedOrder", null, order);
    }

    public String newId(CustomerServerSocketHandler customerServerSocketHandler) {
        connections.add(customerServerSocketHandler);
        customerCounter++;
        return "table" + (customerCounter-1);
    }

    public String newId(ChefServerSocketHandler chefServerSocketHandler) {
        connections.add(chefServerSocketHandler);
        chefCounter++;
        return "chef" + (chefCounter-1);
    }

    //FOR WAITER
    public void sendNotification(Notification notification){
        support.firePropertyChange("Notification added", null, notification);
    }

    public void removeConnection(String tableId) {
        for(int i = 0; i < connections.size(); ++i)
        {
            if(connections.get(i).getId().equals(tableId))
            {
                connections.remove(i);
                break;
            }
        }
    }
}
