package server.model;

import BasicClasses.Order;
import server.SSHIds;
import server.networking.ServerSocketHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerModel {

    private ArrayList<Order> orders;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private ArrayList<SSHIds> connections;
    private int counter;

    public ServerModel() {
        orders= new ArrayList<Order>();
        counter = 0;
        connections = new ArrayList<SSHIds>();
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

    public String newId(ServerSocketHandler serverSocketHandler) {
        connections.add(new SSHIds(serverSocketHandler, "table" + counter));
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
