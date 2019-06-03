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
        sendToWaiter.add(new Order(order.getTableId()));
        changeSupport.firePropertyChange("OrderForChefAdded", null, orders);
    }

    @Override
    public void checkLogIn(String value) {
        chefClient.checkPassword(value);
    }

    @Override
    public void passwordDisapproved() {
        changeSupport.firePropertyChange("passwordDisapproved", null, null);
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
        if(sendToWaiter.size() != 0 && sendToWaiter.get(i).getNumberOfItems() > 0)
        {
            chefClient.sendPartial(sendToWaiter.get(i));
            sendToWaiter.get(i).clearItems();
        }
        else changeSupport.firePropertyChange("noItemsSelected", null, null);
    }

    @Override
    public void nextState(String id, ItemState state, int selectedIndex) {
        Order o = orders.get(selectedIndex);
        switch (o.getItemWithQuantity(id, state).getState())
        {
            case notStarted:{
                o.getItemWithQuantity(id, state).changeState(ItemState.inProgress);
                break;
            }
            case inProgress:{
                o.getItemWithQuantity(id, state).changeState(ItemState.done);
                break;
            }
            case done:{
                o.getItemWithQuantity(id, state).changeState(ItemState.forWaiter);
                sendToWaiter.get(selectedIndex).addItem(o.getItemWithQuantity(id, ItemState.forWaiter));
                break;
            }
        }
        o.checkStates();
        chefClient.itemStateChanged(o);
    }


    @Override
    public void orderFinished(int lastSelected) {
        if(orders.size() != 0 && orders.get(lastSelected).isReadyToBeClosed())
            chefClient.orderFinished(orders.get(lastSelected).getTableId());

    }

    public void removeOrder(String id)
    {
        for(int i = 0; i < orders.size(); ++i)
        {
            if(orders.get(i).getTableId().equals(id))
            {
                orders.remove(i);
                changeSupport.firePropertyChange("orderRemoved", null, i);
                break;
            }
        }
    }

    @Override
    public void requestWaiter() {
        chefClient.requestWaiter();
    }

    @Override
    public void partialSent(Order obj) {
        addedToOrder(obj);
    }

    @Override
    public void sendFinishedItems(int lastSelected) {
        if(orders.size() != 0 && orders.get(lastSelected).canBeSent())
        {
            for(int i = 0; i < orders.get(lastSelected).getNumberOfItems(); ++i)
            {
                if(orders.get(lastSelected).getItemWithQuantity(i).getState() == ItemState.forWaiter)
                    sendToWaiter.get(lastSelected).addItem(orders.get(lastSelected).getItemWithQuantity(i));
            }
            if(!orders.get(lastSelected).isReadyToBeClosed())
                sendPartial(lastSelected);
        }
    }
}
