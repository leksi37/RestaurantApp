package server.model;

import JDBC.OrderReader;
import basicClasses.*;
import server.ServerSocketHandlerClientIds;
import server.networking.ServerSocketHandlers.ChefServerSocketHandler;
import server.networking.ServerSocketHandlers.CustomerServerSocketHandler;
import server.networking.ServerSocketHandlers.ServerSocketHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerModel {
    private ArrayList<Order> orders;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private int customerCounter;
    private DBHandler dbHandler;

    public ServerModel() {
        orders= new ArrayList<>();
        customerCounter = 0;
        dbHandler = new DBHandler();
        orders = dbHandler.getAllOrders();
    }

    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    public void addOrder(Order order){
        int i = 0;
        for(i = 0; i < orders.size(); ++i)
        {
            if(orders.get(i).getTableId().equals(order.getTableId())) {
                orders.get(i).addToOrder(order);
                dbHandler.addOrder(orders.get(i));
                support.firePropertyChange("AddedToOrder", null, orders.get(i));
                break;
            }
        }
        if(i == orders.size())
        {
            orders.add(order);
            dbHandler.addOrder(order);
            support.firePropertyChange("AddedOrder", null, order);
        }
    }

    public String newId() {
        customerCounter++;
        return "table" + (customerCounter-1);
    }

    public void removeConnection() {
        customerCounter--;
    }

    public ArrayList<Order> getOrders() {
        return dbHandler.getAllOrders();
    }

    public void itemStateChanged(Order obj) {
        System.out.println(obj.getTableId());
        for(int i = 0; i < orders.size(); ++i)
        {
            System.out.println(orders.get(i).getTableId());
            if(orders.get(i).getTableId().equals(obj.getTableId()))
            {
                orders.remove(i);
                orders.add(i, obj);
                dbHandler.addOrder(obj);
                support.firePropertyChange("ChangedState", null, obj);
                break;
            }
        }
    }

    public void sendPartial(Order obj) {
        Order o = dbHandler.getOrder(obj.getTableId());
        for(int i = 0; i < obj.getNumberOfItems(); ++i)
        {
            o.getItemWithQuantity(obj.getItemWithQuantity(i).getId()).changeState(ItemState.toWaiter);
        }
        orders.remove(o);
        orders.add(o);
        dbHandler.addOrder(o);
        System.out.println("waiter receives " + obj);
        support.firePropertyChange("partialForWaiterSent", obj, o);
    }

    public void orderFinished(String obj) {
        for(int i = 0; i < orders.size(); ++i)
        {
            if(orders.get(i).getTableId().equals(obj))
            {
                dbHandler.removeOrder(obj);
                orders.remove(i);
                break;
            }
        }
        support.firePropertyChange("orderRemoved", null, obj);
    }

    public void checkPassword(Passwords password) {
        Passwords p = dbHandler.getPassword(password.getName());
        if(password.equals(p))
            support.firePropertyChange("passwordCheck", null, true);
        else
            support.firePropertyChange("passwordCheck", null, false);
    }

    public ArrayList<MenuItem> getMenuItems(type value) {
        return dbHandler.getCategory(value);
    }

    public void chefRequestsWaiter() {
        System.out.println("WAITEEEEEEER");
        support.firePropertyChange("chefRequestsWaiter", null, null);
    }
}
