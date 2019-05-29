package client.model.customer;

import basicClasses.CategoryType;
import basicClasses.ItemQuantity;
import basicClasses.MenuItem;
import basicClasses.Order;
import client.networking.customer.CustomerClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class CustomerModelImpl implements CustomerModel {

    private MenuProxy proxy;
    private CustomerClient customerClient;
    private Order order;
    private String tableId;
    private CategoryType lastSelectedCategory;

    private PropertyChangeSupport support;

    public CustomerModelImpl() {
        this.customerClient = null;
        support = new PropertyChangeSupport(this);
        proxy = new MenuProxy();
    }

    @Override
    public void addListeners(String name, PropertyChangeListener listener) {
        if (name == null)
            support.addPropertyChangeListener(listener);
        else support.addPropertyChangeListener(name, listener);
    }

    @Override //taken from ClientModelInterface in modelFactory
    public void addClient(Object object) {
        if(object instanceof CustomerClient)
        {
            this.customerClient = (CustomerClient) object;
            customerClient.getTableId();
        }
    }

    @Override
    public void addOrderToServer(String note) {
        order.setNote(note);
        System.out.println("customer model, sending order " + order);
        customerClient.addOrderToServer(order);
    }

    @Override
    public void requestMenuCategory(String type) {
        ArrayList menuCategory = proxy.getCategory(type);
        lastSelectedCategory = CategoryType.valueOf(type);
        if(menuCategory == null)
        {
            System.out.println("entered in if");
            customerClient.requestMenuCategory(type);
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
        ArrayList<MenuItem> menuCategory = proxy.getCategory(lastSelectedCategory.name());
        for(int i = 0; i < menuCategory.size(); ++i)
        {
            if(itemId.equals(menuCategory.get(i).getId()))
                order.addItem(new ItemQuantity(menuCategory.get(i), quantity));

        }
    }

    @Override
    public void gotTableId(String str) {
        tableId = str;
        order = new Order(tableId);
    }

    @Override
    public void removeItem(String id, int i) {
        order.removeItem(id, i);
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
        order = new Order(tableId);
        support.firePropertyChange("orderAdded", null, null);
    }

    @Override
    public void requestWaiter() {
        customerClient.requestWaiter(tableId);
    }

    @Override
    public String getPrice() {
        return "Price: " + order.getPrice() + "kr";
    }

}
