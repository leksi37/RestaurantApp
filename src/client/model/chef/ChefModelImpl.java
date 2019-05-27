package client.model.chef;

import basicClasses.ItemState;
import basicClasses.Order;
import client.networking.chef.ChefClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChefModelImpl implements ChefModel {
    private ChefClient chefClient;
    private PropertyChangeSupport changeSupport;
    private ArrayList<Order> orders;
    private ArrayList<Order> sendToWaiter;

    public ChefModelImpl(){
        chefClient = null;
        changeSupport = new PropertyChangeSupport(this);
        orders = new ArrayList<Order>();
        sendToWaiter = new ArrayList<Order>();
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
        chefClient.fetchOrders();
    }

    @Override
    public void gotOrders(ArrayList<Order> obj) {
        orders = obj;
        for(int i = 0; i < orders.size(); ++i)
        {
            sendToWaiter.add(new Order(orders.get(i).getTableId()));
        }
        changeSupport.firePropertyChange("gotOrders", null, obj);
    }

    @Override
    public Order getOrder(int selectedIndex) {
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

    private void addToSendToWaiter(String id, int selectedIndex)
    {
        String tableId = orders.get(selectedIndex).getTableId();
        for(int i = 0; i < sendToWaiter.size(); ++i)
        {
            if(sendToWaiter.get(i).getTableId().equals(tableId))
            {
                sendToWaiter.get(i).addItem(orders.get(selectedIndex).getItemWithQuantity(id));
            }
        }
    }

    private Order getPartial(int selectedIndex)
    {
        String tableId = orders.get(selectedIndex).getTableId();
        for(int i = 0; i < sendToWaiter.size(); ++i)
        {
            if(sendToWaiter.get(i).getTableId().equals(tableId))
                return sendToWaiter.get(i);
        }
        return null;
    }

    @Override
    public void sendPartial(int i) {
//        chefClient.sendPartial(getPartial(i));
    }

    @Override
    public void nextState(String id, int selectedIndex) {
        Order o = orders.get(selectedIndex);
        switch (o.getItemWithQuantity(id).getState())
        {
            case notStarted:{
                o.getItemWithQuantity(id).changeState(ItemState.inProgress);
                break;
            }
            case inProgress:{
                o.getItemWithQuantity(id).changeState(ItemState.done);
                break;
            }
            case done:{
                o.getItemWithQuantity(id).changeState(ItemState.toWaiter);
                sendToWaiter.get(selectedIndex).addItem(o.getItemWithQuantity(id));
            }
        }
        System.out.println("model " + o);
        chefClient.itemStateChanged(o);
    }

    @Override
    public void orderChanged(Order order) {
//        for(int i = 0; i < orders.size(); ++i)
//            if(orders.get(i).getTableId().equals(order.getTableId()))
//            {
//                orders.remove(i);
//                orders.add(i, order);
//            }
//        changeSupport.firePropertyChange("orderChanged", null, obj);
    }

}
