package server.model;

import BasicClasses.Order;
import server.ServerSocketHandlerClientIds;
import server.networking.ServerSocketHandlers.CustomerServerSocketHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModel {

    private ArrayList<Order> orders;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private ArrayList<ServerSocketHandlerClientIds> connections;
    private int counter;

    public ServerModel() {
        orders= new ArrayList<>();
        counter = 0;
        connections = new ArrayList<>();
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
        connections.add(new ServerSocketHandlerClientIds(customerServerSocketHandler, "table" + counter));
        counter++;
        return "table" + (counter-1);
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
