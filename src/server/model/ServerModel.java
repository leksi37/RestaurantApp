package server.model;
import basicClasses.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModel {
    private ArrayList<Order> orders;
    private ArrayList<Notification> notifications;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private int customerCounter;
    private DBHandler dbHandler;

    public ServerModel() {
        orders= new ArrayList<>();
        customerCounter = 0;
        dbHandler = new DBHandler();
        orders = dbHandler.getAllOrders();
        notifications = new ArrayList<Notification>();
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
        orders.remove(o);
        for(int i = 0; i < obj.getNumberOfItems(); ++i)
        {
            o.getItemWithQuantity(obj.getItemWithQuantity(i).getId()).changeState(ItemState.toWaiter);
        }
        orders.add(o);
        dbHandler.addOrder(o);
        Notification n = new Notification("Order to deliver for table " + o.getTableId().charAt(5), o);
        notifications.add(n);
        support.firePropertyChange("partialForWaiter", null, n);
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

    public ArrayList<MenuItem> getMenuItems(CategoryType value) {
        return dbHandler.getCategory(value);
    }

    public void chefRequestsWaiter() {
        Notification n = new Notification("Chefs need waiter's assistance", null);
        notifications.add(n);
        support.firePropertyChange("chefRequestsWaiter", null, n);
    }

    public void customerRequest(String obj) {
        Notification n = new Notification("Customer at table " + Character.getNumericValue(obj.charAt(5))+1 + " requests assistance.", obj);
        notifications.add(n);
        support.firePropertyChange("customerRequest", null, n);
    }
}
