package client.model.customer;

import BasicClasses.ItemQuantity;
import BasicClasses.MenuItem;
import BasicClasses.Order;
import client.networking.customer.Client;
import client.viewModel.MenuProxy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CustomerModelImpl implements CustomerModel {

    private MenuProxy proxy;
    private Client client;
    private Order order;
    private String tableId;

    private PropertyChangeSupport support;

    public CustomerModelImpl() {
        this.client = null;
        support = new PropertyChangeSupport(this);
        proxy = new MenuProxy();

    }

    @Override
    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void addClient(Client client) {
        this.client=client;
        client.getTableId();
        order = new Order(tableId);
    }

    @Override
    public void addOrderToServer() {
        client.addOrderToServer(order);
        order = new Order(tableId);
    }

    @Override
    public void getFromServer() {
        System.out.println("Something came from the server to the client");
    }

    @Override
    public void menuCategory(ArrayList a) {

    }

    @Override
    public void requestMenuCategory(String type) {
        ArrayList menuCategory = proxy.getCategory(type);
        if(menuCategory == null)
        {
            client.requestMenuCategory(type);
        }
        else
            gotMenuItems(menuCategory);

    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> mi) {
        support.firePropertyChange("MenuItems", null, mi);
        proxy.addCategory(mi.get(0).getType().name(), mi);
    }
    public void addItem(String itemId, int quantity)
    {
        order.addItem(itemId, quantity);
        System.out.println(order.dbFormat());
    }

    @Override
    public void gotTableId(String str) {
        tableId = str;
        order = new Order(tableId);
    }

    @Override
    public void removeItem(String id, int i) {
        order.removeItem(id, i);
        System.out.println(order.dbFormat());
    }

    public Order getOrder()
    {
        return order;
    }

    @Override
    public void removeItem(ItemQuantity focusedItem) {
        order.removeItem(focusedItem.getId(), focusedItem.getQuantity());
        support.firePropertyChange("orderChanged", null, order);
    }

    @Override
    public void orderAdded() {
        support.firePropertyChange("orderAdded", null, null);
    }
}
